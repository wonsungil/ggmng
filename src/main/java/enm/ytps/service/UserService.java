package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.User;
import enm.ytps.service.ads.GoogleAdsUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private GoogleAdsUserService googleAdsUserService;
    
    public UserService(GoogleAdsUserService googleAdsUserService) {
         this.googleAdsUserService = googleAdsUserService;
    }
    
    public User[] getUserList(String orderBy, int page, int perPage) {
        return googleAdsUserService.getUserList(orderBy, page, perPage);
    }

    public User[] getUserList(int page, int perPage) {
        return getUserList(null, page, perPage);    
    }
    
    public User[] getUserList() {
        return getUserList(null, 1, 20);
    }
    
    public User getUserById(Long userId) {
        return googleAdsUserService.getUserById(userId);
    }
}
