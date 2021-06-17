package enm.ytps.batch.step;

import com.google.api.ads.admanager.axis.v202005.ContentBundle;
import enm.ytps.batch.reader.ContentBundleRemoteItemReader;
import enm.ytps.model.AmContentBundle;
import enm.ytps.repository.ContentBundleRepository;
import enm.ytps.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ContentBundleStep {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final ContentBundleRemoteItemReader contentBundleRemoteItemReader;
    private final ContentBundleRepository contentBundleRepository;
    
    private final int chunkSize = 50;
    
    @Bean
    @JobScope
    public Step contentBundleSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amContentBundleSaveStep")
                .<ContentBundle, AmContentBundle> chunk(chunkSize)
                .reader(contentBundleRemoteItemReader)
                .processor(contentBundleItemProcessor())
                .writer(contentBundleItemWriter())
                .build();
    } 
    
    @Bean
    @StepScope
    public ItemProcessor<ContentBundle, AmContentBundle> contentBundleItemProcessor() {
        return cb -> {
            LocalDateTime lastModifiedDateTime = 
                    DateUtil.convertGoogleDateTimeToLocalDateTime(cb.getLastModifiedDateTime());
                    
            return AmContentBundle.builder()
                    .id(cb.getId())
                    .name(cb.getName())
                    .status(cb.getStatus().getValue())
                    .lastModifiedDateTime(lastModifiedDateTime)
                    .build();
        };
    }
    
    @Bean
    @StepScope
    public ItemWriter<AmContentBundle> contentBundleItemWriter() {
        return chunkList -> { 
            for (AmContentBundle cb : chunkList) {
                contentBundleRepository.save(cb);
            }
        };
    }
}
