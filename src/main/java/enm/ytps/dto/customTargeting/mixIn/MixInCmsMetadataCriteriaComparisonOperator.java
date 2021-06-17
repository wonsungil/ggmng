package enm.ytps.dto.customTargeting.mixIn;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class MixInCmsMetadataCriteriaComparisonOperator {
    MixInCmsMetadataCriteriaComparisonOperator() {};
    MixInCmsMetadataCriteriaComparisonOperator(@JsonProperty("value") String value) {};

    @JsonProperty("value") abstract String getValue();
}
