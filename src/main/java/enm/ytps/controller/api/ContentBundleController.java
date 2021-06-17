package enm.ytps.controller.api;

import enm.ytps.service.ContentBundleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/contentBundle")
@Controller
public class ContentBundleController {
    private ContentBundleService contentBundleService;
    
    public ContentBundleController(ContentBundleService contentBundleService) {
        this.contentBundleService = contentBundleService;
    }
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(contentBundleService.getContentBundleList(), HttpStatus.OK);
    }
} 
