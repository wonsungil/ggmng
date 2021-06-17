package enm.ytps.controller.api;

import enm.ytps.service.VideoPositionTargetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/videoPositionTarget")
@Controller
public class VideoPositionTargetController {
    
    private VideoPositionTargetService videoPositionTargetService;
    
    public VideoPositionTargetController(VideoPositionTargetService videoPositionTargetService) {
        this.videoPositionTargetService = videoPositionTargetService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getVideoPositionTargetList() {
        return new ResponseEntity<>(videoPositionTargetService.getVideoPositionList(), HttpStatus.OK);
    }
    
}
