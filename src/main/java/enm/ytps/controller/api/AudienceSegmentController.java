package enm.ytps.controller.api;

import enm.ytps.service.ads.GoogleAdsAudienceSegmentService;
import org.eclipse.persistence.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.RemoteException;

@RequestMapping(value = "/audienceSegment")
@Controller
public class AudienceSegmentController {

    @Autowired
    private GoogleAdsAudienceSegmentService googleAdsAudienceSegmentService;
    
    @RequestMapping(value = "/getAudienceSegmentList", method = RequestMethod.GET)
    public @ResponseBody  ResponseEntity getAudienceSegmentList() throws RemoteException {
        return new ResponseEntity<>(googleAdsAudienceSegmentService.getFirstPartyAudienceSegmentList2(null, null), HttpStatus.OK);
    } 
}
