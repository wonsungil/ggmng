package enm.ytps.repository;

import enm.ytps.model.AmCmsMetadataValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsMetaDataValueRepository extends CrudRepository<AmCmsMetadataValue, Long> {
}
