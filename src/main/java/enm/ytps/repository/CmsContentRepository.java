package enm.ytps.repository;

import enm.ytps.model.AmCmsContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsContentRepository extends CrudRepository<AmCmsContent, Long> {
}
