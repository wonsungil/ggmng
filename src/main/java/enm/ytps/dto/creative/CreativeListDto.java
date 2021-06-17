package enm.ytps.dto.creative;

import com.google.api.ads.admanager.axis.v202005.Creative;
import com.google.api.ads.admanager.axis.v202005.Company;
import lombok.Data;

@Data
public class CreativeListDto {
    private Creative creative;
    private Company advertiser;
}
