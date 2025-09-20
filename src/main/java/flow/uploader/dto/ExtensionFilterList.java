package flow.uploader.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtensionFilterList {

    private final int maxCustomFilterCount;

    private final List<ExtensionFilter> fixedExtensionFilter;

    private final List<ExtensionFilter> customExtensionFilter;

    public static ExtensionFilterList create(final int maxCustomFilterCount,
                                             final List<ExtensionFilter> fixedExtensionFilter,
                                             final List<ExtensionFilter> customExtensionFilter) {
        return new ExtensionFilterList(maxCustomFilterCount, fixedExtensionFilter, customExtensionFilter);
    }
}
