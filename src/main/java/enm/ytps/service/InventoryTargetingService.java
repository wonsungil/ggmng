package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.AdUnitTargeting;
import com.google.api.ads.admanager.axis.v202005.InventoryTargeting;
import enm.ytps.service.ads.GoogleAdsInventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryTargetingService {
    
    private GoogleAdsInventoryService googleAdsInventoryService;
    
    public InventoryTargetingService(GoogleAdsInventoryService googleAdsInventoryService) {
        this.googleAdsInventoryService = googleAdsInventoryService;
    }

    /**
     * Inventory Targeting
     *
     * @return
     */
    public InventoryTargeting getInventoryTargeting(Long adUnitId) {
        
        // Youtube to CJ E&M Shared, 57056544
        InventoryTargeting inventoryTargeting = new InventoryTargeting();
        inventoryTargeting.setTargetedAdUnits(new AdUnitTargeting[] {new AdUnitTargeting(Long.toString(adUnitId), true)});
        return inventoryTargeting;
    }

    /**
     * Ad Unit
     */
    
    
    
}
