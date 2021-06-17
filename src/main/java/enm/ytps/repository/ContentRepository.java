package enm.ytps.repository;

import enm.ytps.model.AmContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends CrudRepository<AmContent, Long> {
}
