package enm.ytps.controller.api;

import enm.ytps.service.CustomFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

@RequestMapping("/customField")
@Controller
public class CustomFieldController {
    
    private CustomFieldService customFieldService;
    
    public CustomFieldController(CustomFieldService customFieldService) {
        this.customFieldService = customFieldService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity customFieldList() {
        return new ResponseEntity<>(customFieldService.getCustomFieldList(), HttpStatus.OK);
    }
}
