package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Team;
import com.google.api.ads.admanager.axis.v202005.TeamPage;
import com.google.api.ads.admanager.axis.v202005.TeamServiceInterface;
import com.google.common.collect.Iterables;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Arrays;

@Service
public class GoogleAdsTeamService {
    
    private TeamServiceInterface amTeamService;
    
    public GoogleAdsTeamService(TeamServiceInterface amTeamService) {
        this.amTeamService = amTeamService;
    }
    
    public Team[] getTeams(String orderBy, int page, int perPage) {
        TeamPage teamPage = null;

        StatementBuilder builder = new StatementBuilder()
                .limit(perPage)
                .orderBy("id DESC");
        
        if (orderBy != null) {
            builder.orderBy(orderBy);
        }
        
        if (page > 1) {
            builder.increaseOffsetBy((page-1) * perPage);
        }
        
        try {
            teamPage = amTeamService.getTeamsByStatement(builder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (teamPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        return teamPage.getResults();
    }
    
    public Team getTeamById(Long teamId) {
        
        TeamPage page = null;

        try {
            page = amTeamService.getTeamsByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", teamId)
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (page == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return Iterables.getOnlyElement(Arrays.asList(page.getResults()));
    }
    
    public Team create(Team team) {
        Team created = null;
        try {
            created = Iterables.getOnlyElement(Arrays.asList(amTeamService.createTeams(new Team[] {team})));
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (created == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.TEAM_CREATE_FAILED.getCode()
                    , ErrorMessages.TEAM_CREATE_FAILED.getMessage()
            );
        }
        
        return created;
    }
    
    public Team update(Team team) {
        Team updated = null;

        try {
            updated = Iterables.getOnlyElement(Arrays.asList(amTeamService.updateTeams(new Team[] {team})));
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (updated == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.TEAM_UPDATE_FAILED.getCode()
                    , ErrorMessages.TEAM_CREATE_FAILED.getMessage());
        }
        
        return updated;
    }
}
