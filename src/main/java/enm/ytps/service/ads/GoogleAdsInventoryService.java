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
public class GoogleAdsInventoryService {
    
    @Autowired
    private InventoryServiceInterface amInventoryService;
    
    @Autowired
    private NetworkServiceInterface amNetworkService;
    
    public AdUnit getAdUnitById(Long adUnitId) {
        AdUnitPage page = null;
        try {
            page = amInventoryService.getAdUnitsByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", adUnitId)
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
    
    public AdUnit[] getChildAdUnitsByParentId(Long parentId) {
        AdUnitPage page = null;
        try {
            page = amInventoryService.getAdUnitsByStatement(new StatementBuilder()
                    .where("parentId = :parentId")
                    .withBindVariableValue("parentId", parentId)
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

    public AdUnit[] getTopLevelAdUnit() {
        AdUnitPage page = null;
        
        try {
            String effectiveRootAdUnitId = amNetworkService.getCurrentNetwork().getEffectiveRootAdUnitId();
            page = amInventoryService.getAdUnitsByStatement(new StatementBuilder()
                    .where("parentId = :parentId")
                    .withBindVariableValue("parentId", effectiveRootAdUnitId)
                    .toStatement());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return page.getResults();
    }

    public AdUnit[] getNetworkLevel() {
        AdUnitPage page = null;

        try {
            String effectiveRootAdUnitId = amNetworkService.getCurrentNetwork().getEffectiveRootAdUnitId();
            page = amInventoryService.getAdUnitsByStatement(new StatementBuilder()
                    .where("id = :id")
                    .withBindVariableValue("id", effectiveRootAdUnitId)
                    .toStatement());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return page.getResults();
    }
}
