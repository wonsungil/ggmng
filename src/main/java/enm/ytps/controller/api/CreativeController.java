package enm.ytps.controller.api;

import enm.ytps.service.CreativeService;
import enm.ytps.service.ads.GoogleAdsCreativeSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/creative")
public class CreativeController {
    
    private CreativeService creativeService;
    private GoogleAdsCreativeSetService googleAdsCreativeSetService;
    
    public CreativeController(CreativeService creativeService, GoogleAdsCreativeSetService googleAdsCreativeSetService) {
        this.creativeService = creativeService;
        this.googleAdsCreativeSetService = googleAdsCreativeSetService;
    }
    
    @RequestMapping(value = "{creativeId}", method = RequestMethod.GET)
    public ResponseEntity getCreative(@PathVariable Long creativeId) {
        return new ResponseEntity<>(creativeService.getCreativeById(creativeId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List> getCreativeList(@RequestParam("page") int page) {
        return new ResponseEntity<>(creativeService.getCreativeList(page), HttpStatus.OK);
    }

    @RequestMapping(value = "/image/list", method = RequestMethod.GET)
    public ResponseEntity<List> getImageCreativeList(@RequestParam("page") int page) {
        return new ResponseEntity<>(creativeService.getImageCreativeList(page), HttpStatus.OK);
    }

    @RequestMapping(value = "/video/list", method = RequestMethod.GET)
    public ResponseEntity<List> getVideoCreativeList(@RequestParam("page") int page, @RequestParam("creativeType") String creativeType) {
        return new ResponseEntity<>(creativeService.getVideoCreativeList(page, creativeType), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/video/size", method = RequestMethod.GET)
    public ResponseEntity<Integer> getVideoCreativeList(@RequestParam("creativeType") String creativeType) {
        return new ResponseEntity<>(creativeService.getVideoCreativeSize(creativeType), HttpStatus.OK);
    }

    @RequestMapping(value = "/set/list", method = RequestMethod.GET)
    public ResponseEntity<List> getCreativeSetList() throws RemoteException {
        return new ResponseEntity<>(Arrays.asList(googleAdsCreativeSetService.getCreativeSetList()), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/set/one", method = RequestMethod.GET)
    public ResponseEntity<List> getCreativeSetOne() throws RemoteException {
        return new ResponseEntity<>(Arrays.asList(googleAdsCreativeSetService.getCreativeSetById()), HttpStatus.OK);
    }

}
