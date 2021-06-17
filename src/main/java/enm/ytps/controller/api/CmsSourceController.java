package enm.ytps.controller.api;

import enm.ytps.service.CmsSourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/cmsSource")
@Controller
public class CmsSourceController {
    
    private CmsSourceService cmsSourceService;
    
    public CmsSourceController(CmsSourceService cmsSourceService) {
        this.cmsSourceService = cmsSourceService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(cmsSourceService.getCmsSourceList(), HttpStatus.OK);
    }
}
