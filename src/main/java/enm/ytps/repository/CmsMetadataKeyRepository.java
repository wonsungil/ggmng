package enm.ytps.repository;

import enm.ytps.model.AmCmsMetadataKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsMetadataKeyRepository extends CrudRepository<AmCmsMetadataKey, Long> {
}
