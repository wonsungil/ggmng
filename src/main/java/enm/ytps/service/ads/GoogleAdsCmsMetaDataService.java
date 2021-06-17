package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsCmsMetaDataService {
    
    @Autowired
    private CmsMetadataServiceInterface amCmsMetaDataService;
    
    public CmsMetadataKey[] getCmsMetaDataKeys(String orderBy, int page, int perPage) {
        CmsMetadataKeyPage cmsMetadataKeysPage = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .where("status = :status")
                .orderBy("id DESC")
                .limit(perPage)
                .withBindVariableValue("status", CmsMetadataKeyStatus.ACTIVE.toString());
        
        if (orderBy != null) {
            statementBuilder.orderBy(orderBy);
        }
        
        if (page > 1) {
            statementBuilder.increaseOffsetBy((page-1) * perPage);
        }
        
        try {
            cmsMetadataKeysPage = amCmsMetaDataService.getCmsMetadataKeysByStatement(statementBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        
        if (cmsMetadataKeysPage == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return cmsMetadataKeysPage.getResults();
    }
    
    public CmsMetadataValue[] getCmsMetaDataValueByCmsKeyId(Long cmsKeyId, String cmsKey) {
        CmsMetadataValuePage page = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .where("cmsKeyId = :cmsKeyId")
                .orderBy("id DESC")
                .limit(1000)
                .withBindVariableValue("cmsKeyId", cmsKeyId);
        
        if (cmsKey != null) {
            statementBuilder.where("cmsKey = :cmsKey").withBindVariableValue("cmsKey", cmsKey);
        }
        
        try{
            page = amCmsMetaDataService.getCmsMetadataValuesByStatement(statementBuilder.toStatement()); 
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
    
    public CmsMetadataValue[] getCmsMetaDataValueByCmsKeyId(Long cmsKeyId) {
        return getCmsMetaDataValueByCmsKeyId(cmsKeyId, null);
    }
}
