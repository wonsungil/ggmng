package enm.ytps.controller.api;

import enm.ytps.service.PlacementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/placement")
@Controller
public class PlacementController {
    
    private PlacementService placementService;
    
    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(placementService.getPlacementList(), HttpStatus.OK);
    }
}
