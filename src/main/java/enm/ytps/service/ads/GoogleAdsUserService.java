package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.User;
import com.google.api.ads.admanager.axis.v202005.UserPage;
import com.google.api.ads.admanager.axis.v202005.UserServiceInterface;
import com.google.common.collect.Iterables;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Arrays;

@Service
public class GoogleAdsUserService {
    
    private UserServiceInterface amUserService;
    
    public GoogleAdsUserService(UserServiceInterface amUserService) {
        this.amUserService = amUserService;
    }
    
    public User[] getUserList(String orderBy, int page, int perPage) {
        UserPage userPage = null;

        StatementBuilder builder = new StatementBuilder().limit(perPage);
        
        if (orderBy != null) {
            builder.orderBy(orderBy);
        }
        
        if (page > 1) {
            builder.increaseOffsetBy((page-1) * perPage);
        }
        
        try {
            userPage = amUserService.getUsersByStatement(builder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }

        if (userPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode(),
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return userPage.getResults();
    }
    
    public User getUserById(Long userId) {
        UserPage page = null;

        try {
            page = amUserService.getUsersByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", userId)
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
}
