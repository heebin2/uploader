package flow.uploader.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExtensionFilterType {

    FIXED("고정 확장자"),
    CUSTOM("커스텀 확장자");

    private final String description;

}
