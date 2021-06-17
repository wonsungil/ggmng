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
public class GoogleAdsCreativeService {
    
    @Autowired
    private CreativeServiceInterface amCreativeService;
    
    public Creative[] getCreativeByOrderId(Long creativeId) {
    	CreativePage page = null;
        try {
            page = amCreativeService.getCreativesByStatement(new StatementBuilder()
                    .where("creativeId = :creativeId")
                    .withBindVariableValue("creativeId", creativeId)
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (page==null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return page.getResults();
    }
    
    public Creative[] getCreatives(String orderBy, int page, int perPage) {
    	CreativePage creativePage = null;

        StatementBuilder statBuilder = new StatementBuilder()
                .orderBy(orderBy)
                .limit(perPage);
        
        if(page > 1) { statBuilder.increaseOffsetBy((page-1) * perPage); }
        
        try {
        	creativePage = amCreativeService.getCreativesByStatement(statBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (creativePage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return creativePage.getResults();
    }

    public Creative getCreativeById(Long creativeId) {
        CreativePage page = null;
        try {
            page = amCreativeService.getCreativesByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", creativeId)
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if(page == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return Iterables.getOnlyElement(Arrays.asList(page.getResults()));
    }
    
    public Creative[] getImageCreatives(String orderBy, int page, int perPage) {
        CreativePage creativePage = null;

        StatementBuilder statBuilder = new StatementBuilder()
                .orderBy(orderBy)
                .where("creativeType = :creativeType")
                .withBindVariableValue("creativeType", "ImageCreative")
                .limit(perPage);

        if(page > 1) { statBuilder.increaseOffsetBy((page-1) * perPage); }

        try {
            creativePage = amCreativeService.getCreativesByStatement(statBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (creativePage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return creativePage.getResults();
    }   
    
    public Creative[] getVideoCreatives(String type, String orderBy, int page, int perPage) {
        CreativePage creativePage = null;

        StatementBuilder statBuilder = new StatementBuilder()
                .orderBy(orderBy)
                .where("creativeType = :creativeType")
                .withBindVariableValue("creativeType", type)
                .limit(perPage);

        if(page > 1) { statBuilder.increaseOffsetBy((page-1) * perPage); }

        try {
            creativePage = amCreativeService.getCreativesByStatement(statBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (creativePage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return creativePage.getResults();
    }

    public int getVideoCreativeSize(String creativeType) {
        CreativePage creativePage = null;

        StatementBuilder statBuilder = new StatementBuilder()
                .where("creativeType in ('VideoRedirectCreative','VideoCreative','VastRedirectCreative')");
        try {
            creativePage = amCreativeService.getCreativesByStatement(statBuilder.toStatement());
            
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (creativePage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return creativePage.getTotalResultSetSize();
    }
}
