package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Placement;
import com.google.api.ads.admanager.axis.v202005.PlacementPage;
import com.google.api.ads.admanager.axis.v202005.PlacementServiceInterface;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsPlacementService {
    
    @Autowired
    private PlacementServiceInterface amPlacementService;
    
    public Placement[] getPlacementList() {
        PlacementPage page = null;

        try {
            page = amPlacementService.getPlacementsByStatement(new StatementBuilder().toStatement());
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
        
        return page.getResults();
    }
}
