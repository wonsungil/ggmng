package enm.ytps.batch.step;

import com.google.api.ads.admanager.axis.v202005.User;
import enm.ytps.batch.reader.UserRemoteItemReader;
import enm.ytps.model.AmUser;
import enm.ytps.repository.UserRepository;
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
public class UserStep {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final UserRemoteItemReader userRemoteItemReader;
    private final UserRepository userRepository;
    
    private final int chunkSize = 50;
    
    @Bean
    @JobScope
    public Step userSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amUserSaveStep")
                .<User, AmUser> chunk(chunkSize)
                .reader(userRemoteItemReader)
                .processor(userItemProcessor())
                .writer(userItemWriter())
                .build();
    }
    
    @Bean
    @StepScope
    public ItemProcessor<User, AmUser> userItemProcessor() {
        return user-> AmUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roleId(user.getRoleId())
                .roleName(user.getRoleName())
                .isActive(user.getIsActive())
                .isEmailNotificationAllowed(user.getIsEmailNotificationAllowed())
                .externalId(user.getExternalId())
                .isServiceAccount(user.getIsServiceAccount())
                .orderUiLocaltimeZoneId(user.getOrdersUiLocalTimeZoneId())
                .build();
    }
    
    @Bean
    @StepScope
    public ItemWriter<AmUser> userItemWriter() {
        return (chunkList) -> {
            for (AmUser user : chunkList) {
                userRepository.save(user);
            }
        };
    }
}
