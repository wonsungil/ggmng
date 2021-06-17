package enm.ytps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.dto.customTargeting.mixIn.*;
import enm.ytps.service.ads.GoogleAdsCustomTargetingService;
import org.springframework.stereotype.Service;

@Service
public class CustomTargetingService {
    
    private GoogleAdsCustomTargetingService googleAdsCustomTargetingService;

    public CustomTargetingService(GoogleAdsCustomTargetingService googleAdsCustomTargetingService) {
        this.googleAdsCustomTargetingService = googleAdsCustomTargetingService;
    }

    public CustomTargetingKey[] getCustomTargetingKeyList() {
        return googleAdsCustomTargetingService.getCustomTargetingKeys();
    }
    
    public CustomTargetingValue[] getCustomTargetingValues(Long customTargetingKeyId) {
        return googleAdsCustomTargetingService.getCustomTargetingValues(customTargetingKeyId);
    }
    
    public CustomCriteriaSet toCustomCriteriaSet(String jsonCustomCriteriaSet) {
        /**
         * CustomTargeting Json Deserialization
         * LineItem 생성 시, Targeting Json 값을 CustomCriteriaSet으로 변환
         * /api/인벤토리/커스텀타게팅/커스텀타게팅테스트 > post data > json string 참고
         */
        CustomCriteriaSet customCriteriaSet = null;
        try {
            customCriteriaSet = new ObjectMapper()
                    .addMixIn(CustomCriteriaSetLogicalOperator.class, MixInCustomCriteriaSetLogicalOperator.class)
                    .addMixIn(CustomCriteriaNode.class, MixInCustomCriteriaNode.class)
                    .addMixIn(CustomCriteriaSet.class, MixInCustomCriteriaSet.class)
                    .addMixIn(CustomCriteriaComparisonOperator.class, MixInCustomCriteriaComparisonOperator.class)
                    .addMixIn(CmsMetadataCriteriaComparisonOperator.class, MixInCmsMetadataCriteriaComparisonOperator.class)
                    .addMixIn(DayOfWeek.class, MixInDayOfWeek.class)
                    .addMixIn(RequestPlatform.class, MixInRequestPlatform.class)
                    .addMixIn(DeliveryTimeZone.class, MixInDeliveryTimeZone.class)
                    .addMixIn(VideoBumperType.class, MixInVideoBumperType.class)
                    .readValue(jsonCustomCriteriaSet, CustomCriteriaSet.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customCriteriaSet;
    }
    
}
