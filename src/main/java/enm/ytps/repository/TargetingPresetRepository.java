package enm.ytps.repository;

import enm.ytps.model.AmTargetingPreset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetingPresetRepository extends CrudRepository<AmTargetingPreset, Long> {
}
