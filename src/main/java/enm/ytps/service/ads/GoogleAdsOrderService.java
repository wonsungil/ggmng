package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import com.google.common.collect.Iterables;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Arrays;

@Service
public class GoogleAdsOrderService {
    
    @Autowired
    private OrderServiceInterface amOrderService;
    
    public Order getOrderById(Long orderId) {
        
        OrderPage page = null;
        try {
            page = amOrderService.getOrdersByStatement(new StatementBuilder()
                    .where("id = :orderId")
                    .withBindVariableValue("orderId", orderId)
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

    public Order[] getOrders(String orderBy, int page, int perPage) {
        OrderPage orderPage = null;
        StatementBuilder builder = new StatementBuilder()
                .orderBy(orderBy)
                .limit(perPage);

        if (page > 1) { builder.increaseOffsetBy((page-1) * perPage);  }
        
        try {
            orderPage = amOrderService.getOrdersByStatement(builder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (orderPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return orderPage.getResults();
    }
    
    public Order createOrder(Order order) {
        Order created = null;
        try {
            created = Iterables.getOnlyElement(Arrays.asList(amOrderService.createOrders(new Order[] {order})));
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (created == null) {
            throw new AdMngNetworkException(ErrorMessages.ORDER_CREATE_FAILED.getCode()
                , ErrorMessages.ORDER_CREATE_FAILED.getMessage());
        }
        
        return created;
    }

    public Order updateOrder(Order order) {
        Order updated = null;
        try {
            updated = Iterables.getOnlyElement(Arrays.asList(amOrderService.updateOrders(new Order[] {order})));
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (updated == null) {
            throw new AdMngNetworkException(ErrorMessages.ORDER_CREATE_FAILED.getCode()
                    , ErrorMessages.ORDER_CREATE_FAILED.getMessage());
        }

        return updated;
    }
}