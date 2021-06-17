package enm.ytps.controller.api;

import enm.ytps.service.GeoTargetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/geoTargeting")
@Controller
public class GeoTargetingController {
    
    private GeoTargetingService geoTargetingService;
    
    public GeoTargetingController(GeoTargetingService geoTargetingService) {
        this.geoTargetingService = geoTargetingService;
    }
    
    @RequestMapping(value = "/kor", method = RequestMethod.GET)
    public ResponseEntity getKorGeoTargeting() {
        return new ResponseEntity<>(geoTargetingService.getKorGeoTarget(), HttpStatus.OK);
    }
}
