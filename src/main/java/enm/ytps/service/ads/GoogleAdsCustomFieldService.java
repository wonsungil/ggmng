package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.CustomField;
import com.google.api.ads.admanager.axis.v202005.CustomFieldPage;
import com.google.api.ads.admanager.axis.v202005.CustomFieldServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Slf4j
@Service
public class GoogleAdsCustomFieldService {
    
    @Autowired
    private CustomFieldServiceInterface amCustomFieldService;
    
    public CustomField[] getCustomFieldList() {

        CustomFieldPage customFieldPage = null;

        try {
            customFieldPage = amCustomFieldService.getCustomFieldsByStatement(new StatementBuilder().limit(100).toStatement());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (customFieldPage == null) {
            log.info("custom Page is null");
        } else {
            log.info("custom page data size : " + customFieldPage.getTotalResultSetSize());
        }

        return customFieldPage.getResults();   
    }
    
}
