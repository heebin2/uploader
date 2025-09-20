package flow.uploader.persistence.repository;

import flow.uploader.common.code.YnType;
import flow.uploader.dto.ExtensionFilterType;
import flow.uploader.persistence.entity.ExtensionFilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExtensionFilterRepository extends JpaRepository<ExtensionFilterEntity, Long> {

    Optional<ExtensionFilterEntity> findByIdAndDeleteYn(final Long id, final YnType deleteYn);

    List<ExtensionFilterEntity> findAllByDeleteYn(final YnType deleteYn);

    Optional<ExtensionFilterEntity> findFirstByNameAndDeleteYn(final String name, final YnType deleteYn);

    long countByExtensionFilterTypeAndDeleteYn(final ExtensionFilterType deleteYn, final YnType useYn);

    boolean existsByNameAndUseYnAndDeleteYn(final String name, final YnType useYn, final YnType deleteYn);
}
