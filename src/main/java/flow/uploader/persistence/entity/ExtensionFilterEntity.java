package flow.uploader.persistence.entity;

import flow.uploader.common.code.YnType;
import flow.uploader.dto.ExtensionFilterType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "extension_filter")
@EqualsAndHashCode(of = "id", callSuper = false)
public class ExtensionFilterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extension_filter_type", nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private ExtensionFilterType extensionFilterType;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "delete_yn", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private YnType deleteYn;

    @Column(name = "use_yn", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private YnType useYn;

    @CreatedDate
    private LocalDateTime createdDatetime;

    @LastModifiedDate
    private LocalDateTime modifiedDatetime;

    public static ExtensionFilterEntity create(final ExtensionFilterType extensionFilterType,
                                               final String name) {
        final ExtensionFilterEntity entity = new ExtensionFilterEntity();
        entity.extensionFilterType = extensionFilterType;
        entity.name = name;
        entity.deleteYn = YnType.N;
        entity.useYn = YnType.Y;

        return entity;
    }

    public ExtensionFilterEntity delete() {
        this.deleteYn = YnType.Y;
        this.useYn = YnType.N;

        return this;
    }

    public ExtensionFilterEntity enable() {
        this.useYn = YnType.Y;

        return this;
    }

    public ExtensionFilterEntity disable() {
        this.useYn = YnType.N;

        return this;
    }
}
