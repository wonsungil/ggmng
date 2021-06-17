package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Contact;
import enm.ytps.service.ads.GoogleAdsContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    
    private GoogleAdsContactService googleAdsContactService;
    
    public ContactService(GoogleAdsContactService googleAdsContactService) {
        this.googleAdsContactService = googleAdsContactService;
    }
    
    public Contact[] getContactList(int page, int perPage) {
        return googleAdsContactService.getContacts(page, perPage);
    }
    
}
