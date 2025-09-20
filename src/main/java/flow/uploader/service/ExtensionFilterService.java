package flow.uploader.service;

import flow.uploader.dto.ExtensionFilter;
import flow.uploader.dto.ExtensionFilterList;

public interface ExtensionFilterService {

    /**
     * 확장자 목록 조회
     */
    ExtensionFilterList getExtensionFilter();

    /**
     * 커스텀 확장자 추가
     */
    ExtensionFilter addCustomExtensionFilter(final String extension);

    /**
     * 커스텀 확장자 삭제
     */
    ExtensionFilter removeCustomExtensionFilter(final Long extensionId);

    /**
     * 고정 확장자 활성화
     */
    ExtensionFilter enableFixedExtensionFilter(final Long extensionId);

    /**
     * 고정 확장자 비활성화
     */
    ExtensionFilter disableFixedExtensionFilter(final Long extensionId);

    /**
     * 허용된 확장자인지 검사
     */
    boolean isDenyExtension(final String fileName);
}
