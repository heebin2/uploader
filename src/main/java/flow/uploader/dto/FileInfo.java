package flow.uploader.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileInfo {

    private String originFileName;

    private String extension;

    private String fileSize;

    public static FileInfo create(final String originFileName, final String extension, final String fileSize) {
        return new FileInfo(originFileName, extension, fileSize);
    }
}
