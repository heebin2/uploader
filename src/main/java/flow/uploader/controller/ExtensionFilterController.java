package flow.uploader.controller;

import flow.uploader.common.response.ApiResponse;
import flow.uploader.controller.response.ExtensionFilterListResponse;
import flow.uploader.controller.response.ExtensionFilterResponse;
import flow.uploader.service.ExtensionFilterService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/extension/filter")
public class ExtensionFilterController {

    private final ExtensionFilterService extensionFilterService;

    @GetMapping
    public ApiResponse<ExtensionFilterListResponse> getExtensionFilter() {

        var extensionFilterList = extensionFilterService.getExtensionFilter();

        return ApiResponse.ok(ExtensionFilterListResponse.ExtensionFilterListResponse(extensionFilterList));
    }

    @PostMapping
    public ApiResponse<ExtensionFilterResponse> addCustomExtensionFilter(
            @NotNull @RequestParam String extension) {

        var filter = extensionFilterService.addCustomExtensionFilter(extension);

        return ApiResponse.ok(ExtensionFilterResponse.create(filter));
    }

    @DeleteMapping("/{extensionId}")
    public ApiResponse<ExtensionFilterResponse> removeCustomExtensionFilter(
            @NotNull @PathVariable Long extensionId) {

        var filter = extensionFilterService.removeCustomExtensionFilter(extensionId);

        return ApiResponse.ok(ExtensionFilterResponse.create(filter));
    }

    @PutMapping("/{extensionId}/enable")
    public ApiResponse<ExtensionFilterResponse> enableFixedExtensionFilter(
            @NotNull @PathVariable Long extensionId) {

        var filter = extensionFilterService.enableFixedExtensionFilter(extensionId);

        return ApiResponse.ok(ExtensionFilterResponse.create(filter));
    }

    @PutMapping("/{extensionId}/disable")
    public ApiResponse<ExtensionFilterResponse> disableFixedExtensionFilter(
            @NotNull @PathVariable Long extensionId) {

        var filter = extensionFilterService.disableFixedExtensionFilter(extensionId);

        return ApiResponse.ok(ExtensionFilterResponse.create(filter));
    }
}
