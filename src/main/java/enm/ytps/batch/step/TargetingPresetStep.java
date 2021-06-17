package enm.ytps.batch.step;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.ads.admanager.axis.v202005.TargetingPreset;
import enm.ytps.batch.reader.TargetingPresetRemoteItemReader;
import enm.ytps.model.AmTargetingPreset;
import enm.ytps.repository.TargetingPresetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TargetingPresetStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final TargetingPresetRemoteItemReader targetingPresetRemoteItemReader;
    private final TargetingPresetRepository targetingPresetRepository;
    
    private final int chunkSize = 50;
    
    @Bean
    @JobScope
    public Step targetingPresetSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amTargetingPresetSaveStep")
                .<TargetingPreset, AmTargetingPreset> chunk(chunkSize)
                .reader(targetingPresetRemoteItemReader)
                .processor(targetingPresetItemProcessor())
                .writer(targetingPresetItemWriter())
                .build();
    } 
    
    @Bean
    @JobScope
    public ItemProcessor<TargetingPreset, AmTargetingPreset> targetingPresetItemProcessor() {
        return targetingPreset -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String targetingJsonValue = objectMapper.writeValueAsString(targetingPreset.getTargeting()); 
            
            return AmTargetingPreset.builder()
                    .id(targetingPreset.getId())
                    .name(targetingPreset.getName())
                    .targetingJsonValue(targetingJsonValue)
                    .build();   
        };
    }
    
    @Bean
    @JobScope
    public ItemWriter<AmTargetingPreset> targetingPresetItemWriter() {
        return (chunkList) -> {
            for(AmTargetingPreset amTargetingPreset : chunkList) {
                targetingPresetRepository.save(amTargetingPreset);
            }
        };
    }
}
