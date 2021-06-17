package enm.ytps.batch.step;

import com.google.api.ads.admanager.axis.v202005.Team;
import enm.ytps.batch.reader.TeamRemoteItemReader;
import enm.ytps.model.AmTeam;
import enm.ytps.repository.TeamRepository;
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
public class TeamStep {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final TeamRemoteItemReader teamRemoteItemReader;
    private final TeamRepository teamRepository;
    
    private final int chunkSize = 50;
    
    @Bean
    @JobScope
    public Step teamSaveStep(@Value("#{jobParameters[requestDateTime]}") String requestDateTime) {
        return stepBuilderFactory.get("amTeamSaveStep")
                .<Team, AmTeam> chunk(chunkSize)
                .reader(teamRemoteItemReader)
                .processor(teamItemProcessor())
                .writer(teamItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Team, AmTeam> teamItemProcessor() {
        return team-> AmTeam.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .status(team.getStatus().getValue())
                .hasAllCompanies(team.getHasAllCompanies())
                .hasAllInventory(team.getHasAllInventory())
                .teamAccessType(team.getTeamAccessType().getValue())
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<AmTeam> teamItemWriter() {
        return (chunkList) -> {
            for (AmTeam team : chunkList) {
                teamRepository.save(team);
            }
        };
    } 

}
