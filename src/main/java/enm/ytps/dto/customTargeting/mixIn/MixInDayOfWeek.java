package enm.ytps.dto.customTargeting.mixIn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.ads.admanager.axis.v202005.TimeOfDay;

public abstract class MixInDayOfWeek {
    MixInDayOfWeek(@JsonProperty("value") String value) {};
    @JsonProperty("value") abstract String getValue();
}
