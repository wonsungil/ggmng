package enm.ytps.dto.creative;

import com.google.api.ads.admanager.axis.v202005.Company;
import com.google.api.ads.admanager.axis.v202005.Creative;
import enm.ytps.dto.creative.CreativeDto;
import lombok.Data;

import java.util.List;

@Data
public class CreativeDto {
    
    private Creative creative;
    private Company advertiser;
    private List<CreativeDto> creativeDtoArrayList;
}
