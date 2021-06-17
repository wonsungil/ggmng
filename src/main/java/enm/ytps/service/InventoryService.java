package enm.ytps.service;

import enm.ytps.service.ads.GoogleAdsInventoryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InventoryService {
    
    private GoogleAdsInventoryService googleAdsInventoryService;

    public InventoryService(GoogleAdsInventoryService googleAdsInventoryService) {
        this.googleAdsInventoryService = googleAdsInventoryService;
    }
}
