package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsContentBundleService {
    
    @Autowired
    private ContentBundleServiceInterface amContentBundleService;
    
    public ContentBundle[] getContentBundleList(String orderBy, int page, int perPage) {
        ContentBundlePage contentBundlePage = null;
        
        StatementBuilder statementBuilder = new StatementBuilder()
                .orderBy("id DESC")
                .limit(perPage);
        
        if (orderBy != null) {
            statementBuilder.orderBy(orderBy);
        }
        
        if (page > 1) {
            statementBuilder.increaseOffsetBy((page-1) * perPage);
        }
        
        try {
            contentBundlePage = amContentBundleService.getContentBundlesByStatement(statementBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (contentBundlePage == null) {
            throw new AdMngNetworkException(ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
            , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return contentBundlePage.getResults();
    }
}
