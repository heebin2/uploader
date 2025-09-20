package flow.uploader.service;

import flow.uploader.dto.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderService {

    /**
     * 파일 업로드
     */
    FileInfo uploadFile(MultipartFile file);

}
