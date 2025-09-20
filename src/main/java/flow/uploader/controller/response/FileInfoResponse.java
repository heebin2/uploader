package flow.uploader.controller.response;

import flow.uploader.dto.FileInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FileInfoResponse {

    private String originFileName;

    private String extension;

    private String fileSize;

    public static FileInfoResponse create(final FileInfo fileInfo) {
        return FileInfoResponse.builder()
                .originFileName(fileInfo.getOriginFileName())
                .extension(fileInfo.getExtension())
                .fileSize(fileInfo.getFileSize())
                .build();
    }
}
