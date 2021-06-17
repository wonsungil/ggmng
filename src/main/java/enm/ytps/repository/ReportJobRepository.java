package enm.ytps.repository;


import enm.ytps.model.ReportJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJobRepository extends CrudRepository<ReportJob, Long> {
}
