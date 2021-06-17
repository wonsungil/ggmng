package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Label;
import com.google.api.ads.admanager.axis.v202005.LabelPage;
import enm.ytps.service.ads.GoogleAdsLabelService;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    
    private GoogleAdsLabelService googleAdsLabelService;
    
    public LabelService(GoogleAdsLabelService googleAdsLabelService) {
        this.googleAdsLabelService = googleAdsLabelService;
    }
    
    public Label[] getLabelList(String orderBy, int page, int perPage) {
        return googleAdsLabelService.getLabels(orderBy, page, perPage);
    }
    
    public Label[] getLabelList(int page, int perPage) {
        return googleAdsLabelService.getLabels(null, page, perPage);
    }

}
