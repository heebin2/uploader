package flow.uploader.service.impl;

import flow.uploader.common.code.ErrorCode;
import flow.uploader.common.code.ProcessException;
import flow.uploader.common.code.YnType;
import flow.uploader.dto.ExtensionFilter;
import flow.uploader.dto.ExtensionFilterList;
import flow.uploader.dto.ExtensionFilterType;
import flow.uploader.persistence.entity.ExtensionFilterEntity;
import flow.uploader.persistence.repository.ExtensionFilterRepository;
import flow.uploader.service.ExtensionFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ExtensionFilterServiceImpl implements ExtensionFilterService {

    private static final int MAX_CUSTOM_FILTER_COUNT = 200;

    private final ExtensionFilterRepository extensionFilterRepository;

    @Override
    @Transactional(readOnly = true)
    public ExtensionFilterList getExtensionFilter() {

        var extensionFilters = extensionFilterRepository.findAllByDeleteYn(YnType.N)
                .stream()
                .map(ExtensionFilter::create)
                .toList();

        var fixedExtensionFilter = extensionFilters.stream()
                .filter(extensionFilter -> ExtensionFilterType.FIXED.equals(extensionFilter.getExtensionFilterType()))
                .toList();

        var customExtensionFilter = extensionFilters.stream()
                .filter(extensionFilter -> ExtensionFilterType.CUSTOM.equals(extensionFilter.getExtensionFilterType()))
                .toList();

        return ExtensionFilterList.create(MAX_CUSTOM_FILTER_COUNT, fixedExtensionFilter, customExtensionFilter);
    }

    @Override
    @Transactional
    public ExtensionFilter addCustomExtensionFilter(final String extension) {

        validateExtension(extension);

        if (extensionFilterRepository.countByExtensionFilterTypeAndDeleteYn(ExtensionFilterType.CUSTOM, YnType.N) >= MAX_CUSTOM_FILTER_COUNT) {
            throw new ProcessException(ErrorCode.EXCEED_MAX_EXTENSION_FILTER, "커스텀 확장자는 최대 개수 초과입니다.");
        }

        var existsExtensionFilterType = extensionFilterRepository.findFirstByNameAndDeleteYn(extension, YnType.N)
                .map(ExtensionFilterEntity::getExtensionFilterType)
                .orElse(null);

        validateCustomExtension(existsExtensionFilterType);

        var extensionFilter = ExtensionFilterEntity.create(ExtensionFilterType.CUSTOM, extension);

        extensionFilterRepository.save(extensionFilter);

        return ExtensionFilter.create(extensionFilter);
    }

    @Override
    @Transactional
    public ExtensionFilter removeCustomExtensionFilter(final Long extensionId) {

        var extensionFilter = extensionFilterRepository.findByIdAndDeleteYn(extensionId, YnType.N)
                .orElseThrow(() -> new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "존재하지 않는 확장자입니다."));

        if (ExtensionFilterType.FIXED.equals(extensionFilter.getExtensionFilterType())) {
            throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "고정 확장자는 삭제할 수 없습니다.");
        }

        extensionFilterRepository.save(extensionFilter.delete());

        return ExtensionFilter.create(extensionFilter);
    }

    @Override
    @Transactional
    public ExtensionFilter enableFixedExtensionFilter(final Long extensionId) {

        var extensionFilter = extensionFilterRepository.findByIdAndDeleteYn(extensionId, YnType.N)
                .orElseThrow(() -> new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "존재하지 않는 확장자입니다."));

        if (ExtensionFilterType.CUSTOM.equals(extensionFilter.getExtensionFilterType())) {
            throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "고정 확장자만 활성화할 수 있습니다.");
        }

        if (YnType.Y.equals(extensionFilter.getUseYn())) {
            return ExtensionFilter.create(extensionFilter);
        }

        extensionFilterRepository.save(extensionFilter.enable());

        return ExtensionFilter.create(extensionFilter);
    }

    @Override
    @Transactional
    public ExtensionFilter disableFixedExtensionFilter(final Long extensionId) {
        var extensionFilter = extensionFilterRepository.findByIdAndDeleteYn(extensionId, YnType.N)
                .orElseThrow(() -> new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "존재하지 않는 확장자입니다."));

        if (ExtensionFilterType.CUSTOM.equals(extensionFilter.getExtensionFilterType())) {
            throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "고정 확장자만 비활성화할 수 있습니다.");
        }

        if (YnType.N.equals(extensionFilter.getUseYn())) {
            return ExtensionFilter.create(extensionFilter);
        }

        extensionFilterRepository.save(extensionFilter.disable());

        return ExtensionFilter.create(extensionFilter);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isDenyExtension(final String fileName) {
        return extensionFilterRepository.existsByNameAndUseYnAndDeleteYn(fileName, YnType.Y, YnType.N);
    }

    private void validateExtension(final String extension) {
        if (ObjectUtils.isEmpty(extension)) {
            throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "확장자가 너무 짧습니다.");
        }

        if (extension.length() > 20) {
            throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "확장자가 너무 깁니다.");
        }

        if (!Character.isLowerCase(extension.charAt(0)) || !Character.isLetter(extension.charAt(0))) {
            throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "확장자는 소문자 또는 숫자로 시작해야 합니다.");
        }

        for (char c : extension.toCharArray()) {
            if (!(Character.isLowerCase(c) || Character.isDigit(c))) {
                throw new ProcessException(ErrorCode.INVALID_EXTENSION_FILTER, "확장자는 소문자 또는 숫자로만 구성되어야 합니다.");
            }
        }
    }

    private static void validateCustomExtension(final ExtensionFilterType existsExtensionFilterType) {
        if (!ObjectUtils.isEmpty(existsExtensionFilterType)) {
            if (ExtensionFilterType.FIXED.equals(existsExtensionFilterType)) {
                throw new ProcessException(ErrorCode.DUPLICATE_EXTENSION_FILTER, "해당 확장자는 고정 확장자입니다. 체크박스로 처리해주세요.");
            } else {
                throw new ProcessException(ErrorCode.DUPLICATE_EXTENSION_FILTER, "이미 등록된 확장자입니다.");
            }
        }
    }
}
