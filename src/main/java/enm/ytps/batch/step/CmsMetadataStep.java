package enm.ytps.batch.step;

import com.google.api.ads.admanager.axis.v202005.CmsMetadataKey;
import com.google.api.ads.admanager.axis.v202005.CmsMetadataValue;
import enm.ytps.batch.reader.CmsMetadataKeyRemoteItemReader;
import enm.ytps.batch.reader.CmsMetadataValueRemoteItemReader;
import enm.ytps.model.AmCmsMetadataKey;
import enm.ytps.model.AmCmsMetadataValue;
import enm.ytps.repository.CmsMetaDataValueRepository;
import enm.ytps.repository.CmsMetadataKeyRepository;
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

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CmsMetadataStep {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final CmsMetadataKeyRemoteItemReader cmsMetadataKeyRemoteItemReader;
//    private final CmsMetadataValueRemoteItemReader cmsMetadataValueRemoteItemReader;
    private final CmsMetaDataValueRepository cmsMetaDataValueRepository;
    private final CmsMetadataKeyRepository cmsMetadataKeyRepository;
    
    private final int chunkSize = 50;
    
    @Bean
    @JobScope
    public Step cmsMetadataKeySaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amCmsMetadataKeySaveStep")
                .<CmsMetadataKey, AmCmsMetadataKey>chunk(chunkSize)
                .reader(cmsMetadataKeyRemoteItemReader)
                .processor(cmsMetadataKeyItemProcessor())
                .writer(cmsMetadataKeyItemWriter())
                .build();
    }
    
//    @Bean
//    @JobScope
//    public Step cmsMetadataValueSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
//        return stepBuilderFactory.get("amCmsMetadataValueSaveStep")
//                .<CmsMetadataValue, AmCmsMetadataValue>chunk(chunkSize)
//                .reader(cmsMetadataValueRemoteItemReader)
//                .processor(cmsMetadataValueItemProcessor())
//                .writer(cmsMetadataValueItemWriter())
//                .build();
//    }
    
    @Bean
    @StepScope
    public ItemProcessor<CmsMetadataKey, AmCmsMetadataKey> cmsMetadataKeyItemProcessor() {
        return key -> AmCmsMetadataKey.builder()
                .id(key.getId())
                .name(key.getName())
                .status(key.getStatus().getValue())
                .build();
    }
    
    @Bean
    @StepScope
    public ItemProcessor<CmsMetadataValue, AmCmsMetadataValue> cmsMetadataValueItemProcessor() {
        return value -> {
            CmsMetadataKey key = value.getKey();
            AmCmsMetadataKey amCmsMetadataKey = AmCmsMetadataKey.builder()
                    .id(key.getId())
                    .status(key.getStatus().getValue())
                    .name(key.getName())
                    .build();
                    
            return AmCmsMetadataValue.builder()
                    .cmsMetadataValueId(value.getCmsMetadataValueId())
                    .valueName(value.getValueName())
//                    .key(amCmsMetadataKey)
                    .status(value.getStatus().getValue())
                    .build();   
        };
    }
    
    @Bean
    @StepScope
    public ItemWriter<AmCmsMetadataKey> cmsMetadataKeyItemWriter() {
        return chunkList -> {
            for (AmCmsMetadataKey amCmsMetadataKey : chunkList) {
                cmsMetadataKeyRepository.save(amCmsMetadataKey);
            }
        };
    }
    
    
    @Bean
    @StepScope
    public ItemWriter<AmCmsMetadataValue> cmsMetadataValueItemWriter() {
        return chunkList -> {
            for (AmCmsMetadataValue amCmsMetadataValue : chunkList) {
                cmsMetaDataValueRepository.save(amCmsMetadataValue);
            }
        };
    }
    
    
    
}
