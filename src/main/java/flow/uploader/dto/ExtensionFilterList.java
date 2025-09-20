package flow.uploader.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtensionFilterList {

    private int maxCustomFilterCount;

    private List<ExtensionFilter> fixedExtensionFilter;

    private List<ExtensionFilter> customExtensionFilter;

    public static ExtensionFilterList create(final int maxCustomFilterCount,
                                             final List<ExtensionFilter> fixedExtensionFilter,
                                             final List<ExtensionFilter> customExtensionFilter) {
        return new ExtensionFilterList(maxCustomFilterCount, fixedExtensionFilter, customExtensionFilter);
    }
}
