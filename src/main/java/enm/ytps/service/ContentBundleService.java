package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.ContentBundle;
import enm.ytps.service.ads.GoogleAdsContentBundleService;
import org.springframework.stereotype.Service;

@Service
public class ContentBundleService {
    
    private GoogleAdsContentBundleService googleAdsContentBundleService;

    public ContentBundleService(GoogleAdsContentBundleService googleAdsContentBundleService) {
        this.googleAdsContentBundleService = googleAdsContentBundleService;
    }

    public ContentBundle[] getContentBundleList(String orderBy, int page, int perPage) {
        return googleAdsContentBundleService.getContentBundleList(orderBy, page, perPage);
    }
    
    public ContentBundle[] getContentBundleList(int page, int perPage) {
        return getContentBundleList(null, page , perPage);
    }
    
    public ContentBundle[] getContentBundleList() {
        return getContentBundleList(null, 1, 20);
    }
}
