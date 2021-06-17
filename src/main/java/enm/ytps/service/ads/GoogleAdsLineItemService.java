package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Arrays;

@Service
public class GoogleAdsLineItemService {
    
    @Autowired
    private LineItemServiceInterface amLineItemService;
    
    public LineItem[] getLineItemsByOrderId(Long orderId) {
        LineItemPage page = null;
        try {
            page = amLineItemService.getLineItemsByStatement(new StatementBuilder()
                    .where("orderId = :orderId")
                    .withBindVariableValue("orderId", orderId)
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
    
    public LineItem[] getLineItems(String orderBy, int page, int perPage) {
        LineItemPage lineItemPage = null;

        StatementBuilder statBuilder = new StatementBuilder()
                .limit(perPage);
        
        if (orderBy!=null) {
            statBuilder.orderBy(orderBy);
        }
        
        if(page > 1) { statBuilder.increaseOffsetBy((page-1) * perPage); }
        
        try {
            lineItemPage = amLineItemService.getLineItemsByStatement(statBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (lineItemPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return lineItemPage.getResults();
    }


    public LineItem getLineItemById(Long lineItemId) {
        LineItemPage page = null;

        try {
            page = amLineItemService.getLineItemsByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", lineItemId)
                    .limit(1)
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
    
    public UpdateResult update(long lineItemId, LineItemAction action) {
        StatementBuilder statementBuilder = new StatementBuilder();
        statementBuilder.where("id = :id")
                .withBindVariableValue("id", lineItemId);
        
        UpdateResult updateResult = null;
        
        try {
            updateResult = amLineItemService.performLineItemAction(action, statementBuilder.toStatement());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return updateResult;
    }
}
