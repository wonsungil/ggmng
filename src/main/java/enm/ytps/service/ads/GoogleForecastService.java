package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.v202005.ForecastServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleForecastService {
    
    @Autowired
    private ForecastServiceInterface amForecastService;
    
    
}
