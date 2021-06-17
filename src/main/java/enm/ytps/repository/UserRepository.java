package enm.ytps.repository;

import enm.ytps.model.AmUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AmUser, Long> {
}
