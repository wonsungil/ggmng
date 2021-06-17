package enm.ytps.batch.step;

import com.google.api.ads.admanager.axis.v202005.UserTeamAssociation;
import enm.ytps.batch.reader.UserTeamAssociationRemoteReader;
import enm.ytps.model.AmUserTeamAssociation;
import enm.ytps.model.AmUserTeamAssociationId;
import enm.ytps.repository.UserTeamAssociationRepository;
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
public class UserTeamAssociationStep {

    private final StepBuilderFactory stepBuilderFactory;
    private final UserTeamAssociationRemoteReader userTeamAssociationRemoteReader;
    private final UserTeamAssociationRepository userTeamAssociationRepository;

    private final int chunkSize = 50;

    @Bean
    @JobScope
    public Step userTeamAssociationSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amUserSaveStep")
                .<UserTeamAssociation, AmUserTeamAssociation> chunk(chunkSize)
                .reader(userTeamAssociationRemoteReader)
                .processor(userTeamAssociationItemProcessor())
                .writer(userTeamAssociationItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<UserTeamAssociation, AmUserTeamAssociation> userTeamAssociationItemProcessor() {
        return uta-> AmUserTeamAssociation.builder()
                .amUserTeamAssociationId(AmUserTeamAssociationId.builder()
                        .teamId(uta.getTeamId())
                        .userId(uta.getUserId())
                        .build()
                )
                .defaultTeamAccessType(uta.getDefaultTeamAccessType()!=null ?
                        uta.getDefaultTeamAccessType().getValue():""
                )
                .overridenTeamAccessType(uta.getOverriddenTeamAccessType()!=null?
                        uta.getOverriddenTeamAccessType().getValue():""
                )
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<AmUserTeamAssociation> userTeamAssociationItemWriter() {
        return (chunkList) -> {
            for (AmUserTeamAssociation userTeamAssociation : chunkList) {
                userTeamAssociationRepository.save(userTeamAssociation);
            }
        };
    }
}
