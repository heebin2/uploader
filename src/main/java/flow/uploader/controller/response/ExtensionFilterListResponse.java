package flow.uploader.controller.response;

import flow.uploader.dto.ExtensionFilterList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ExtensionFilterListResponse {

    private final int maxCustomFilterCount;

    private List<ExtensionFilterResponse> fixedExtensionFilter;

    private List<ExtensionFilterResponse> customExtensionFilter;

    public static ExtensionFilterListResponse ExtensionFilterListResponse(final ExtensionFilterList extensionFilterList) {
        return ExtensionFilterListResponse.builder()
                .maxCustomFilterCount(extensionFilterList.getMaxCustomFilterCount())
                .fixedExtensionFilter(extensionFilterList.getFixedExtensionFilter()
                        .stream()
                        .map(ExtensionFilterResponse::create)
                        .toList())
                .customExtensionFilter(extensionFilterList.getCustomExtensionFilter()
                        .stream()
                        .map(ExtensionFilterResponse::create)
                        .toList())
                .build();
    }
}

