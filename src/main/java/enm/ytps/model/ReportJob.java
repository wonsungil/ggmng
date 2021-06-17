package enm.ytps.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "report_jobs")
public class ReportJob {
    
    @Id
    @Column(name="id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="job_id", unique = false, updatable = false, nullable = false)
    private Long jobId;
    
    @Builder
    public ReportJob(Long jobId) {
        this.jobId = jobId;
    }
}
