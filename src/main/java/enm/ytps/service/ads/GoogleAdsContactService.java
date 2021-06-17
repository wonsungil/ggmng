package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Contact;
import com.google.api.ads.admanager.axis.v202005.ContactPage;
import com.google.api.ads.admanager.axis.v202005.ContactServiceInterface;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsContactService {
    
    private ContactServiceInterface amContactService;
    
    public GoogleAdsContactService(ContactServiceInterface amContactService) {
        this.amContactService = amContactService;
    }
    
    public Contact[] getContacts(int page, int perPage) {

        ContactPage contactPage = null;
        
        StatementBuilder statementBuilder = new StatementBuilder()
                .limit(perPage);
        
        if (page > 1) {
            statementBuilder.increaseOffsetBy((page-1)*perPage);
        }

        try {
            contactPage = amContactService.getContactsByStatement(statementBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (contactPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return contactPage.getResults();
    }
    
}
