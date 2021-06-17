package enm.ytps.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TargetingPresetJob {
    
    private final JobBuilderFactory jobBuilderFactory;
    private final Step targetingPresetSaveStep;
    
    @Bean
    public Job targetingPresetSaveJob() {
        return jobBuilderFactory.get("amTargetingPresetSaveJob")
                .start(targetingPresetSaveStep)
                .build();
    }
}
