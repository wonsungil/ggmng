package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.CreativeSet;
import com.google.api.ads.admanager.axis.v202005.CreativeSetPage;
import com.google.api.ads.admanager.axis.v202005.CreativeSetServiceInterface;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Arrays;

@Service
public class GoogleAdsCreativeSetService {
    
    @Autowired
    private CreativeSetServiceInterface amCreativeSetService;
    
    public CreativeSet[] getCreativeSetList() throws RemoteException {

        StatementBuilder statementBuilder = new StatementBuilder()
                .where("id = 723794955")
                .orderBy("id DESC")
                .limit(10);
        
        CreativeSetPage creativeSetPage = amCreativeSetService.getCreativeSetsByStatement(statementBuilder.toStatement());
        return creativeSetPage.getResults();
    }

    public CreativeSet getCreativeSetById() throws RemoteException {
        StatementBuilder statementBuilder = new StatementBuilder()
                .where("masterCreativeId = 138344052685")
                .orderBy("id DESC")
                .limit(10);

        CreativeSetPage creativeSetPage = amCreativeSetService.getCreativeSetsByStatement(statementBuilder.toStatement());
        return Iterables.getOnlyElement(Arrays.asList(creativeSetPage.getResults()));
    }
}
