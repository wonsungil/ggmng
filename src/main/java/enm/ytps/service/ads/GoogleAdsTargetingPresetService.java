package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Targeting;
import com.google.api.ads.admanager.axis.v202005.TargetingPreset;
import com.google.api.ads.admanager.axis.v202005.TargetingPresetPage;
import com.google.api.ads.admanager.axis.v202005.TargetingPresetServiceInterface;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsTargetingPresetService {
    
    @Autowired
    private TargetingPresetServiceInterface amTargetingPresetService;
    
    public TargetingPreset[] getTargetingPresetList(int page, int perPage) {

        TargetingPresetPage targetingPresetPage = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .limit(perPage)
                .orderBy("id DESC");
        
        if (page > 1) {
            statementBuilder.increaseOffsetBy((page-1)*perPage);   
        }
        
        try {
            targetingPresetPage = amTargetingPresetService.getTargetingPresetsByStatement(statementBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    ,  ErrorMessages.CONNECTION_ERROR.getMessage()
                    ,  e.getCause());
        }
        
        if (targetingPresetPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return targetingPresetPage.getResults();
    }
    
}
