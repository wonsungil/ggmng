package enm.ytps.repository;

import enm.ytps.model.AmMedia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<AmMedia, Long> {
}
