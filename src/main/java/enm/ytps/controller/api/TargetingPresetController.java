package enm.ytps.controller.api;

import com.google.api.ads.admanager.axis.v202005.Targeting;
import com.google.api.ads.admanager.axis.v202005.TargetingPreset;
import enm.ytps.service.TargetingPresetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@RequestMapping("/targetingPreset")
@Controller
public class TargetingPresetController {
    private TargetingPresetService targetingPresetService;
    
    public TargetingPresetController(TargetingPresetService targetingPresetService) {
        this.targetingPresetService = targetingPresetService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(targetingPresetService.getTargetingPresetList(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/fromDb/{targetingPresetId}", method = RequestMethod.GET)
    public ResponseEntity targetingPreset(@PathVariable("targetingPresetId") long targetingPresetId) {
        //113171326588
        TargetingPreset targetingPreset = targetingPresetService.getTargetingPresetFromDB(targetingPresetId);
        log.info(targetingPreset.toString());
        return new ResponseEntity<>(targetingPreset, HttpStatus.OK);
    }
}
