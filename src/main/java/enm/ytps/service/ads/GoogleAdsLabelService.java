package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Label;
import com.google.api.ads.admanager.axis.v202005.LabelPage;
import com.google.api.ads.admanager.axis.v202005.LabelServiceInterface;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsLabelService {
    
    private LabelServiceInterface amLabelService;
    
    public GoogleAdsLabelService(LabelServiceInterface labelServiceInterface) {
        this.amLabelService = labelServiceInterface;
    }
    
    public Label[] getLabels(String orderBy, int page, int perPage) {

        LabelPage labelPage = null;

        StatementBuilder builder = new StatementBuilder().limit(perPage);
        
        if (orderBy != null) {
            builder.orderBy(orderBy);
        }
        
        if (page > 1) {
            builder.increaseOffsetBy((page-1) * perPage);
        }

        try {
            labelPage = amLabelService.getLabelsByStatement(builder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (labelPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        return labelPage.getResults();
    }
}
