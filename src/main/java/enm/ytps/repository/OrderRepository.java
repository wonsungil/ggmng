package enm.ytps.repository;

import enm.ytps.model.AmOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<AmOrder, Long> {
}
