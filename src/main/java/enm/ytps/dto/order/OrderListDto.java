package enm.ytps.dto.order;

import com.google.api.ads.admanager.axis.v202005.Company;
import com.google.api.ads.admanager.axis.v202005.Order;
import lombok.Data;

@Data
public class OrderListDto {
    private Order order;
    private Company advertiser;
}
