package enm.ytps.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@Entity
@Table(name = "am_report")
public class AmReport {
    
    @Id
    private long id;
    private long agencyId;
    private long agencyUserId;
    private long advertiserId;
    private String advertiserName;
    private String brandName;
    private long orderId;
    private String orderName;
    private String lineItemId;
    private String creativeId;
    private String type;
    
    private String ytChannel;
        
}
