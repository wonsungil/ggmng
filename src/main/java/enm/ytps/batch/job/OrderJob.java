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
public class OrderJob {

    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    private final Step orderSaveStep;

    @Autowired
    private final Step contactSaveStep;

    @Autowired
    private final Step userSaveStep;

    @Autowired
    private final Step teamSaveStep;
    
    @Bean
    public Job orderSaveJob() {
        return jobBuilderFactory.get("amOrderInsertJob")
                .start(contactSaveStep)
                .next(userSaveStep)
                .next(teamSaveStep)
                .next(orderSaveStep)
                .build();
    }

}
