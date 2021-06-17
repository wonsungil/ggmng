package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.CustomTargetingValue;
import enm.ytps.service.ads.GoogleAdsCustomTargetingService;
import org.springframework.stereotype.Service;

@Service
public class CmsSourceService {
    
    private GoogleAdsCustomTargetingService googleAdsCustomTargetingService;
    
    public CmsSourceService(GoogleAdsCustomTargetingService googleAdsCustomTargetingService) {
        this.googleAdsCustomTargetingService = googleAdsCustomTargetingService;
    }
    
    public CustomTargetingValue[] getCmsSourceList() {
        return googleAdsCustomTargetingService.getCmsSourceValues();   
    }
    
}
