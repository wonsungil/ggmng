package enm.ytps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.dto.customTargeting.mixIn.*;
import enm.ytps.model.AmTargetingPreset;
import enm.ytps.repository.TargetingPresetRepository;
import enm.ytps.service.ads.GoogleAdsTargetingPresetService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TargetingPresetService {
    
    private GoogleAdsTargetingPresetService googleAdsTargetingPresetService;
    private TargetingPresetRepository targetingPresetRepository;
    
    public TargetingPresetService(GoogleAdsTargetingPresetService googleAdsTargetingPresetService, TargetingPresetRepository targetingPresetRepository) {
        this.googleAdsTargetingPresetService = googleAdsTargetingPresetService;
        this.targetingPresetRepository = targetingPresetRepository;
    }
    
    public TargetingPreset[] getTargetingPresetList(int page, int perPage) {
        return googleAdsTargetingPresetService.getTargetingPresetList(page, perPage);
    }
    
    public TargetingPreset[] getTargetingPresetList() {
        return getTargetingPresetList(1, 20);
    }
    
    public TargetingPreset getTargetingPresetFromDB(long targetingPresetId) {
        //113171239217
        Optional<AmTargetingPreset> targetingPresetVo = targetingPresetRepository.findById(targetingPresetId);
        
        TargetingPreset targetingPreset = null;
        Targeting targeting = null;
        if(targetingPresetVo.isPresent()) {
            try {
                targeting = new ObjectMapper()
                        .addMixIn(CustomCriteriaSetLogicalOperator.class, MixInCustomCriteriaSetLogicalOperator.class)
                        .addMixIn(CustomCriteriaComparisonOperator.class, MixInCustomCriteriaComparisonOperator.class)
                        .addMixIn(CmsMetadataCriteriaComparisonOperator.class, MixInCmsMetadataCriteriaComparisonOperator.class)
                        .addMixIn(CustomCriteriaSet.class, MixInCustomCriteriaSet.class)
                        .addMixIn(CustomCriteriaNode.class, MixInCustomCriteriaNode.class)
                        .addMixIn(VideoPositionType.class, MixInVideoPositionType.class)
                        .addMixIn(DayOfWeek.class, MixInDayOfWeek.class)
                        .addMixIn(RequestPlatform.class, MixInRequestPlatform.class)
                        .addMixIn(DeliveryTimeZone.class, MixInDeliveryTimeZone.class)
                        .addMixIn(VideoBumperType.class, MixInVideoBumperType.class)
                        .readValue(targetingPresetVo.get().getTargetingJsonValue(), Targeting.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            targetingPreset = new TargetingPreset();
            targetingPreset.setId(targetingPresetVo.get().getId());
            targetingPreset.setName(targetingPresetVo.get().getName());
            targetingPreset.setTargeting(targeting);
        }
        return targetingPreset;
    }
}
