package enm.ytps.repository;

import enm.ytps.model.AmContentBundle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentBundleRepository extends CrudRepository<AmContentBundle, Long> {
}
