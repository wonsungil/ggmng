package enm.ytps.dto.customTargeting.mixIn;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonTypeResolver(CustomCriteriaNodeResolver.class)
public abstract class MixInCustomCriteriaNode {
    
    
}
