package flow.uploader.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileInfo {

    private final String originFileName;

    private final String extension;

    private final String fileSize;

    public static FileInfo create(final String originFileName, final String extension, final String fileSize) {
        return new FileInfo(originFileName, extension, fileSize);
    }
}
