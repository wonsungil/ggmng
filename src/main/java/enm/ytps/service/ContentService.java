package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Content;
import enm.ytps.service.ads.GoogleAdsContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    
    private GoogleAdsContentService googleAdsContentService;

    public ContentService(GoogleAdsContentService googleAdsContentService) {
        this.googleAdsContentService = googleAdsContentService;
    }

    public Content[] getContentList(String orderBy, int page, int perPage) {
        return googleAdsContentService.getContentList(orderBy, page, perPage);
    } 
    
    public Content[] getContentList(int page, int perPage) {
        return getContentList(null, page, perPage);
    }
    
    public Content[] getContentList() {
        return getContentList(null, 1, 20);
    }
    
}
