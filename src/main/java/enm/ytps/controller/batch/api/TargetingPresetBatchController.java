package enm.ytps.controller.batch.api;


import enm.ytps.repository.TargetingPresetRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@RequestMapping(value = "/batch/targetingPreset")
@Controller
public class TargetingPresetBatchController {
    
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job targetingPresetSaveJob;
    
    public TargetingPresetBatchController(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }
    
    @RequestMapping(value = "/runTargetingPresetSaveJob", method = RequestMethod.GET)
    public ResponseEntity runTargetingPresetSaveJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameter jobParameter = new JobParameter(LocalDateTime.now().toString());
        JobExecution contactJob = jobLauncher.run(targetingPresetSaveJob, new JobParameters(
                new LinkedHashMap<String, JobParameter>() {
                    {
                        put("requestDateTime", jobParameter);
                    }
                }));
        return new ResponseEntity(HttpStatus.OK);
    }
}
