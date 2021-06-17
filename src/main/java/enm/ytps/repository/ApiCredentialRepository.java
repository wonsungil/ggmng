package enm.ytps.repository;

import enm.ytps.model.AmApiCredential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCredentialRepository extends CrudRepository<AmApiCredential, Long> {
}
