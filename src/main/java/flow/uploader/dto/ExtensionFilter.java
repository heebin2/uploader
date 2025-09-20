package flow.uploader.dto;

import flow.uploader.common.code.YnType;
import flow.uploader.persistence.entity.ExtensionFilterEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@EqualsAndHashCode(of = "id")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtensionFilter {

    @NotNull
    private Long id;

    @NotNull
    private ExtensionFilterType extensionFilterType;

    private YnType useYn;

    @NotNull
    private String name;

    public static ExtensionFilter create(final ExtensionFilterEntity entity) {
        return ExtensionFilter.builder()
                .id(entity.getId())
                .extensionFilterType(entity.getExtensionFilterType())
                .useYn(entity.getUseYn())
                .name(entity.getName())
                .build();
    }
}
