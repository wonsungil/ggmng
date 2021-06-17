package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.ResultSet;
import com.google.api.ads.admanager.axis.v202005.Row;
import enm.ytps.service.ads.GoogleAdsPqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceTargetingService {
    
    private GoogleAdsPqlService googleAdsPqlService;
    
    public DeviceTargetingService(GoogleAdsPqlService googleAdsPqlService) {
        this.googleAdsPqlService = googleAdsPqlService;
    }
    
    public void selectDeviceList() {
        ResultSet rs = googleAdsPqlService.getOsList();
        if (rs!=null && rs.getRows() != null) {
            for (Row row : rs.getRows()) {
                log.info(row.toString());   
            }
        }
    }
}
