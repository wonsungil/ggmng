package enm.ytps.dto.customTargeting.mixIn;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.ads.admanager.axis.v202005.CustomCriteriaNode;
import com.google.api.ads.admanager.axis.v202005.CustomCriteriaSetLogicalOperator;

public abstract class MixInCustomCriteriaSet {
    MixInCustomCriteriaSet(@JsonProperty("logicalOperator") CustomCriteriaSetLogicalOperator logicalOperator,
                           @JsonProperty("children") CustomCriteriaNode[] children) { }
                           
    @JsonProperty("logicalOperator") abstract CustomCriteriaSetLogicalOperator getLogicalOperator();
    @JsonProperty("logicalOperator") abstract void setLogicalOperator(CustomCriteriaSetLogicalOperator logicalOperator);
    @JsonProperty("children") abstract CustomCriteriaNode[] getChildren();
    @JsonProperty("children") abstract void setChildren(CustomCriteriaNode[] children);
        

}
