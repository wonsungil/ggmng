package enm.ytps.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CmsMetadataJob {
    private final JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private final Step cmsMetadataKeySaveStep;
    
//    @Autowired
//    private final Step cmsMetadataValueSaveStep;
    
    @Bean
    public Job cmsMetadataKeySaveJob() {
        return jobBuilderFactory.get("amCmsMetadataKeySaveJob")
                .start(cmsMetadataKeySaveStep)
                .build();
    }
}
