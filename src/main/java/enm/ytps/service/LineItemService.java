package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.service.ads.GoogleAdsLineItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class LineItemService {
    
    private GoogleAdsLineItemService googleAdsLineItemService;

    public LineItemService(GoogleAdsLineItemService googleAdsLineItemService) {
        this.googleAdsLineItemService = googleAdsLineItemService;
    }

    public List getLineItemList(String orderBy, int page, int perPage) {
        return Arrays.asList(googleAdsLineItemService.getLineItems(orderBy, page, perPage));
    }

    public LineItem[] getLineItemList(int page, int perPage) {
        return googleAdsLineItemService.getLineItems(null, page,perPage);
    }
    
    public List getLineItemList(int page) {
        return getLineItemList(null, page, 10);
    }

    public LineItem getLineItemById(Long lineItemId) {
        return googleAdsLineItemService.getLineItemById(lineItemId);
    }
    
    public UpdateResult updateStatus(long lineItemId, String actionName) {
        LineItemAction action = null;

         switch(actionName) {
             case "activate": action = new ActivateLineItems(); break;
             case "resume": action = new ResumeLineItems(); break;
             case "pause": action = new PauseLineItems(); break;
             case "release": action = new ReleaseLineItems(); break;
             case "delete": action = new DeleteLineItems(); break;
             case "reserve": action = new ReserveLineItems(); break;
             case "archive" : action = new ArchiveLineItems(); break;
             case "unarchive": action = new UnarchiveLineItems(); break;
        }
        
        return googleAdsLineItemService.update(lineItemId, action);
    }
}
