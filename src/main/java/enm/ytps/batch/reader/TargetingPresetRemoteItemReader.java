package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.TargetingPreset;
import enm.ytps.service.TargetingPresetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@StepScope
public class TargetingPresetRemoteItemReader implements ItemReader<TargetingPreset> {

    private final TargetingPresetService targetingPresetService;
    private List<TargetingPreset> targetingPresetList = new LinkedList<>();

    private int page = 1;
    private int perPage = 100;
    private int totalCount = 0;
    
    @Override
    public TargetingPreset read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(targetingPresetList.isEmpty()) {
            TargetingPreset[] targetingPresets = targetingPresetService.getTargetingPresetList(page, perPage);
            
            if (targetingPresets == null) {
                return null;
            }
            
            targetingPresetList.addAll(Arrays.asList(targetingPresets));
            page++;
        }
        
        if(targetingPresetList.isEmpty()) {
            return null;
        }
        
        return targetingPresetList.remove(0);
    }
}
