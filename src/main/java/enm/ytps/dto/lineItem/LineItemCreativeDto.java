package enm.ytps.dto.lineItem;

import com.google.api.ads.admanager.axis.v202005.Creative;
import com.google.api.ads.admanager.axis.v202005.LineItem;
import com.google.api.ads.admanager.axis.v202005.LineItemCreativeAssociation;
import lombok.Data;

@Data
public class LineItemCreativeDto {
    
    private LineItem lineItem;
    private Creative creative;
    private LineItemCreativeAssociation lineItemCreativeAssociation;
}
