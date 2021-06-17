package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.Team;
import enm.ytps.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@StepScope
public class TeamRemoteItemReader implements ItemReader<Team> {
    
    private final TeamService teamService;
    private List<Team> teamList = new LinkedList<>();
    
    private int page = 1;
    private int perPage = 100;
    private int totalCount = 0;

    @Override
    public Team read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        if (teamList.isEmpty()) {
            Team[] teams = teamService.getTeams(page, perPage);
            
            if (teams == null) {
                return null;
            }
            
            teamList.addAll(Arrays.asList(teams));
            page++;
        }
        
        if (teamList.isEmpty()) {
            return null;
        }
        
        return teamList.remove(0);
    }
}
