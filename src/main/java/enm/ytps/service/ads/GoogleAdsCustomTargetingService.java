package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsCustomTargetingService {
    
    @Autowired
    private CustomTargetingServiceInterface amCustomTargetingService;
    
    public CustomTargetingKey[] getCustomTargetingKeys() {
        CustomTargetingKeyPage page = null;
        try {
            page = amCustomTargetingService.getCustomTargetingKeysByStatement(new StatementBuilder()
                        .orderBy("id DESC")
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
        
        return page.getResults();
    }
    
    public CustomTargetingValue[] getCustomTargetingValues(Long customTargetingKeyId) {
        CustomTargetingValuePage page = null;
        try {
            page = amCustomTargetingService.getCustomTargetingValuesByStatement(new StatementBuilder()
                    .where("customTargetingKeyId = :customTargetingKeyId")
                    .withBindVariableValue("customTargetingKeyId", customTargetingKeyId)
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
        
        return page.getResults();
    }
    
    
    public CustomTargetingValue[] getCmsSourceValues() {
        CustomTargetingValuePage page = null;
        
        try {
            page = amCustomTargetingService.getCustomTargetingValuesByStatement(new StatementBuilder()
                    .where("customTargetingKeyId = :customTargetingKeyId")
                    .orderBy("id DESC")
                    .withBindVariableValue("customTargetingKeyId", 330504)
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
        
        return page.getResults();
    }
    
    
}
