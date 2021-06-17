package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.AdUnit;
import enm.ytps.service.ads.GoogleAdsInventoryService;
import org.springframework.stereotype.Service;

@Service
public class AdUnitService {
    
    private GoogleAdsInventoryService googleAdsInventoryService;
    
    public AdUnitService(GoogleAdsInventoryService googleAdsInventoryService) {
        this.googleAdsInventoryService = googleAdsInventoryService;
    }

    // 57056544
    public AdUnit getAdUnit(Long adUnitId) {
        return googleAdsInventoryService.getAdUnitById(adUnitId);
    }
    
    public AdUnit[] getTopLevelAdUnit() {
        return googleAdsInventoryService.getTopLevelAdUnit();
    }

    public AdUnit[] getNetworkLevel() {
        return googleAdsInventoryService.getNetworkLevel();
    }
}
