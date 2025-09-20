package flow.uploader.service.impl;

import flow.uploader.common.code.ErrorCode;
import flow.uploader.common.code.ProcessException;
import flow.uploader.dto.FileInfo;
import flow.uploader.service.ExtensionFilterService;
import flow.uploader.service.FileUploaderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploaderServiceImpl implements FileUploaderService {

    private final ExtensionFilterService extensionFilterService;

    @Override
    @Transactional(readOnly = true)
    public FileInfo uploadFile(MultipartFile file) {
        var originalFileName = file.getOriginalFilename();
        if (ObjectUtils.isEmpty(originalFileName)) {
            throw new ProcessException(ErrorCode.INVALID_FILE_NAME);
        }

        var extension = getExtension(originalFileName);

        if (extensionFilterService.isDenyExtension(extension)) {
            throw new ProcessException(ErrorCode.NOT_ALLOW_EXTENSION_FILE);
        }

        var fileSize = FileUtils.byteCountToDisplaySize(file.getSize());

        return FileInfo.create(originalFileName, extension, fileSize);
    }

    private String getExtension(String originalFileName) {
        if (!originalFileName.contains(".")) {
            throw new ProcessException(ErrorCode.INVALID_FILE_NAME);
        }

        return originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
    }
}
