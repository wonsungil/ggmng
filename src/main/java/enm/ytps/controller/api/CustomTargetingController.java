package enm.ytps.controller.api;

import com.google.api.ads.admanager.axis.v202005.CustomCriteriaNode;
import com.google.api.ads.admanager.axis.v202005.CustomCriteriaSet;
import enm.ytps.service.CustomTargetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/customTargeting")
@Controller
public class CustomTargetingController {
    
    private CustomTargetingService customTargetingService;
    
    public CustomTargetingController(CustomTargetingService customTargetingService) {
        this.customTargetingService = customTargetingService;
    }
    
    @RequestMapping(value = "/keyList", method = RequestMethod.GET)
    public ResponseEntity getKeyList() {
        return new ResponseEntity<>(customTargetingService.getCustomTargetingKeyList(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/valueListByKey/{customTargetingKeyId}", method = RequestMethod.GET)
    public ResponseEntity getValueListByKey(@PathVariable Long customTargetingKeyId) {
        return new ResponseEntity<>(customTargetingService.getCustomTargetingValues(customTargetingKeyId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/createCustomTargeting", method = RequestMethod.POST)
    public ResponseEntity createCustomTargeting(@RequestBody String request) {
        CustomCriteriaSet customCriteriaSet = customTargetingService.toCustomCriteriaSet(request);
        System.out.println(customCriteriaSet.toString());
        for (CustomCriteriaNode item : customCriteriaSet.getChildren()) {
            System.out.println("\t" + item.toString());

            if (item instanceof CustomCriteriaSet) {
                for (CustomCriteriaNode inner1Item: ((CustomCriteriaSet) item).getChildren()) {
                    System.out.println("\t\t" +  inner1Item.toString());
                }
            }
        }
        return null;
    }
}
