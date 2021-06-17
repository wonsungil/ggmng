package enm.ytps.service;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.LineItemCreativeAssociationPage;
import com.google.api.ads.admanager.axis.v202005.LineItemCreativeAssociationServiceInterface;
import com.google.api.ads.admanager.axis.v202005.LineItemCreativeAssociation;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class LineItemCreativeAssociationService {
    
    private LineItemCreativeAssociationServiceInterface amLICAService;

    public LineItemCreativeAssociationService(LineItemCreativeAssociationServiceInterface amLICAService) {
        this.amLICAService = amLICAService;
    }

    public LineItemCreativeAssociation[] getLICAByLineItemId(long lineItemId) throws RemoteException {
        StatementBuilder statementBuilder = new StatementBuilder().
                where("lineItemId = :lineItemId")
                .withBindVariableValue("lineItemId", lineItemId);

        LineItemCreativeAssociationPage page = amLICAService.getLineItemCreativeAssociationsByStatement(statementBuilder.toStatement());
        LineItemCreativeAssociation[] licas = page.getResults();
        
        if(licas != null) {
            return licas;
        }
        return null;
    }
}
