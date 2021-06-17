package enm.ytps.repository;

import enm.ytps.model.AmTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<AmTeam, Long> {
}
