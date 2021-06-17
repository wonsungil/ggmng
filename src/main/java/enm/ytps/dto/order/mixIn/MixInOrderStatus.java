package enm.ytps.dto.order.mixIn;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class MixInOrderStatus {
    MixInOrderStatus(@JsonProperty("value") String value) {};
    
    @JsonProperty("value") abstract String getValue();
}

