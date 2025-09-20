package flow.uploader.controller.response;

import flow.uploader.common.code.YnType;
import flow.uploader.dto.ExtensionFilter;
import flow.uploader.dto.ExtensionFilterType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ExtensionFilterResponse {

    private Long id;

    private ExtensionFilterType extensionFilterType;

    private YnType useYn;

    private String name;

    public static ExtensionFilterResponse create(ExtensionFilter extensionFilter) {
        return ExtensionFilterResponse.builder()
                .id(extensionFilter.getId())
                .extensionFilterType(extensionFilter.getExtensionFilterType())
                .useYn(extensionFilter.getUseYn())
                .name(extensionFilter.getName())
                .build();
    }
}
