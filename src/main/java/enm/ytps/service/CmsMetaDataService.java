package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.CmsMetadataKey;
import com.google.api.ads.admanager.axis.v202005.CmsMetadataValue;
import enm.ytps.service.ads.GoogleAdsCmsMetaDataService;
import org.springframework.stereotype.Service;

@Service
public class CmsMetaDataService {
    
    private GoogleAdsCmsMetaDataService googleAdsCmsMetaDataService;

    public CmsMetaDataService(GoogleAdsCmsMetaDataService googleAdsCmsMetaDataService) {
        this.googleAdsCmsMetaDataService = googleAdsCmsMetaDataService;
    }

    public CmsMetadataKey[] getKeyList(String orderBy, int page, int perPage) {
        return googleAdsCmsMetaDataService.getCmsMetaDataKeys(orderBy, page, perPage);
    }
    
    public CmsMetadataKey[] getKeyList(int page, int perPage) {
        return getKeyList(null, page, perPage);
    }
    
    public CmsMetadataKey[] getKeyList() {
        return getKeyList(null, 1, 20);
    }
    
    public CmsMetadataValue[] getValuesByCmsKeyId(Long cmsKeyId) {
        return googleAdsCmsMetaDataService.getCmsMetaDataValueByCmsKeyId(cmsKeyId);
    }
}
