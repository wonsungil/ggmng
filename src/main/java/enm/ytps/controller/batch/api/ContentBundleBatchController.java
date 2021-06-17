package enm.ytps.controller.batch.api;

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

@RequestMapping(value = "/batch/contentBundle")
@Controller
public class ContentBundleBatchController {
    
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job contentBundleSaveJob;
    
    public ContentBundleBatchController(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }
    
    @RequestMapping(value = "/runContentBundleSaveJob", method = RequestMethod.GET)
    public ResponseEntity runContentBundleSaveJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameter jobParameter = new JobParameter(LocalDateTime.now().toString());
        JobExecution contactJob = jobLauncher.run(contentBundleSaveJob, new JobParameters(
                new LinkedHashMap<String, JobParameter>() {
                    {
                        put("requestDateTime", jobParameter);
                    }
                }));
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
