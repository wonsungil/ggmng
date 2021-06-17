package enm.ytps.dto.order;

import com.google.api.ads.admanager.axis.v202005.Order;
import enm.ytps.dto.lineItem.LineItemCreativeDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    
    private Order order;
    private List<LineItemCreativeDto> lineItemCreativeDtoArrayList;
}
