package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Content;
import com.google.api.ads.admanager.axis.v202005.ContentPage;
import com.google.api.ads.admanager.axis.v202005.ContentServiceInterface;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsContentService {
    
    @Autowired
    private ContentServiceInterface amContentService;
    
    public Content[] getContentList(String orderBy, int page, int perPage) {
        ContentPage contentPage = null;
        
        StatementBuilder statementBuilder = new StatementBuilder()
                .limit(perPage)
                .orderBy("id DESC");

        if (orderBy != null) {
            statementBuilder.orderBy(orderBy);
        }
        
        if (page > 1) {
            statementBuilder.increaseOffsetBy((page-1) * perPage);
        }
        
        try {
            contentPage = amContentService.getContentByStatement(statementBuilder.toStatement());
            
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (contentPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return contentPage.getResults();
    }
    
}
