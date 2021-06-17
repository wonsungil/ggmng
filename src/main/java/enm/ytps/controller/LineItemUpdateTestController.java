package enm.ytps.controller;

import com.google.api.ads.admanager.axis.v202005.ComputedStatus;
import enm.ytps.service.LineItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Slf4j
@RequestMapping(value = "/lineItemTest")
@Controller
public class LineItemUpdateTestController {
    
    @Autowired
    private LineItemService lineItemService;
    
    @RequestMapping(value = "/{lineItemId}", method = RequestMethod.GET)
    public ResponseEntity lineItem(@PathVariable("lineItemId") long lineItemId) {
        log.info("lineItemId = "+lineItemId);
        return new ResponseEntity<>(lineItemService.getLineItemById(lineItemId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/doIt/{lineItemId}/{action}", method = RequestMethod.GET)
    public ResponseEntity update(@PathVariable("lineItemId") long lineItemId, @PathVariable("action") String action) {
        
        String[] statusStr = new String[] {
                "activate","resume", "pause", "release"
//                , "delete"
                , "reserve", "archive", "unarchive"
        };
        
        if (!Arrays.asList(statusStr).contains(action)) {
            new ResponseEntity<>("inValid status", HttpStatus.OK);
        }
        
        return new ResponseEntity<>(lineItemService.updateStatus(lineItemId, action), HttpStatus.OK);
    }
}
