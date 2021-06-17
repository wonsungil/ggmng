package enm.ytps.repository;

import enm.ytps.model.AmContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<AmContact, Long> {
}
