package enm.ytps.dto.customTargeting.mixIn;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class MixInDeliveryTimeZone {
    MixInDeliveryTimeZone(@JsonProperty("value") String value) {};
    @JsonProperty("value") abstract String getValue();
}
