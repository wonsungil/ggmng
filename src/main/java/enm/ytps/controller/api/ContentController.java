package enm.ytps.controller.api;

import enm.ytps.service.ContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/content")
@Controller
public class ContentController {
    
    private ContentService contentService;
    
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    
    @RequestMapping("/list")
    public ResponseEntity list() {
        return new ResponseEntity<>(contentService.getContentList(), HttpStatus.OK);
    }
    
}
