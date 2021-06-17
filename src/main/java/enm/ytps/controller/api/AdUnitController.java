package enm.ytps.controller.api;

import enm.ytps.service.AdUnitService;
import enm.ytps.service.InventoryTargetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/adUnit")
@Controller
public class AdUnitController {
    
    private AdUnitService adUnitService;
    
    public AdUnitController(AdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }
    
    @RequestMapping(value = "/{adUnitId}", method = RequestMethod.GET)
    public ResponseEntity getAdUnitById(@PathVariable Long adUnitId) {
        return new ResponseEntity<>(adUnitService.getAdUnit(adUnitId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getTopLevel", method = RequestMethod.GET)
    public ResponseEntity getTopLevel() {
        return new ResponseEntity<>(adUnitService.getTopLevelAdUnit(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getNetworkLevel", method = RequestMethod.GET)
    public ResponseEntity getNetworkLevel() {
        return new ResponseEntity<>(adUnitService.getNetworkLevel(), HttpStatus.OK);
    }
}
