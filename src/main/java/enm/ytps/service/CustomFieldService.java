package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.CustomField;
import enm.ytps.service.ads.GoogleAdsCustomFieldService;
import org.springframework.stereotype.Service;

@Service
public class CustomFieldService {
    
    private GoogleAdsCustomFieldService googleAdsCustomFieldService;
    
    public CustomFieldService(GoogleAdsCustomFieldService googleAdsCustomFieldService) {
        this.googleAdsCustomFieldService = googleAdsCustomFieldService;
    }
    
    public CustomField[] getCustomFieldList() {
        return googleAdsCustomFieldService.getCustomFieldList();
    }
}
