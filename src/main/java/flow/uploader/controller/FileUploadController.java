package flow.uploader.controller;

import flow.uploader.common.response.ApiResponse;
import flow.uploader.controller.response.FileInfoResponse;
import flow.uploader.service.FileUploaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploaderService fileUploaderService;

    @PostMapping
    public ApiResponse<FileInfoResponse> uploadFile(@RequestParam("file") MultipartFile file) {

        var fileInfo = fileUploaderService.uploadFile(file);

        return ApiResponse.ok(FileInfoResponse.create(fileInfo));
    }

}
