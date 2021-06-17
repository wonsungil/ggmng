package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Team;
import enm.ytps.service.ads.GoogleAdsTeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    
    private GoogleAdsTeamService googleAdsTeamService;
    
    public TeamService(GoogleAdsTeamService googleAdsTeamService) {
        this.googleAdsTeamService = googleAdsTeamService;
    }
    
    public Team[] getTeams(String orderBy, int page, int perPage) {
        return googleAdsTeamService.getTeams(orderBy, page, perPage);
    }
    
    public Team[] getTeams(int page, int perPage) {
        return getTeams(null, page, perPage);
    }
    
    public Team[] getTeams() {
        return getTeams(null, 1, 20);
    }
    
    public Team getTeamById(Long teamId) {
        return googleAdsTeamService.getTeamById(teamId);
    }
    
    public Team create(Team team) {
        return googleAdsTeamService.create(team);
    }
    
    public Team update(Team team) {
        return googleAdsTeamService.update(team);
    }
}
