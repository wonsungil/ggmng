package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.UserTeamAssociation;
import com.google.api.ads.admanager.axis.v202005.UserTeamAssociationPage;
import com.google.api.ads.admanager.axis.v202005.UserTeamAssociationServiceInterface;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleUserTeamAssociationService {
    
    @Autowired
    private UserTeamAssociationServiceInterface amUserTeamAssociationService;
        
    public UserTeamAssociation[] getUserTeamAssociations(String orderBy, int page, int perPage) {

        UserTeamAssociationPage utaPage = null;
        
        StatementBuilder builder = new StatementBuilder().limit(perPage);
        
        if (orderBy != null) {
            builder.orderBy(orderBy);
        }
        
        if (page > 1) {
            builder.increaseOffsetBy((page-1)*perPage);
        }

        try {
            utaPage = amUserTeamAssociationService.getUserTeamAssociationsByStatement(builder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        if (utaPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return utaPage.getResults();
    }

}
