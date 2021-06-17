package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Placement;
import enm.ytps.service.ads.GoogleAdsPlacementService;
import org.springframework.stereotype.Service;

@Service
public class PlacementService {
    
    private GoogleAdsPlacementService googleAdsPlacementService;
    
    public PlacementService(GoogleAdsPlacementService googleAdsPlacementService) {
        this.googleAdsPlacementService = googleAdsPlacementService;
    }
    
    public Placement[] getPlacementList() {
        return googleAdsPlacementService.getPlacementList();
    }
    
    
}
