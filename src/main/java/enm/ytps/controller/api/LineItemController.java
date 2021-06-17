package enm.ytps.controller.api;

import enm.ytps.service.DeviceTargetingService;
import enm.ytps.service.LineItemCreativeAssociationService;
import enm.ytps.service.LineItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.rmi.RemoteException;
import java.util.List;

@Controller
@RequestMapping("/lineItem")
public class LineItemController {
    
    private LineItemService lineItemService;
    private LineItemCreativeAssociationService lineItemCreativeAssociationService; 
    private DeviceTargetingService deviceTargetingService;
    
    public LineItemController(
            LineItemService lineItemService
            , LineItemCreativeAssociationService lineItemCreativeAssociationService
            , DeviceTargetingService deviceTargetingService) {
        this.lineItemService = lineItemService;
        this.lineItemCreativeAssociationService = lineItemCreativeAssociationService;
        this.deviceTargetingService = deviceTargetingService;
    }
    
    @RequestMapping(value = "{lineItemId}", method = RequestMethod.GET)
    public ResponseEntity getLineItem(@PathVariable Long lineItemId) {
        return new ResponseEntity<>(lineItemService.getLineItemById(lineItemId), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List> getLineItemList(@RequestParam("page") int page) {
        return new ResponseEntity<>(lineItemService.getLineItemList(page), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/lica/{lineItemId}", method = RequestMethod.GET)
    public ResponseEntity getLicaByLineitemId(@PathVariable Long lineItemId) throws RemoteException {
        return new ResponseEntity<>(lineItemCreativeAssociationService.getLICAByLineItemId(lineItemId), HttpStatus.OK);
    }

    @RequestMapping(value = "/deviceList", method = RequestMethod.GET)
    public ResponseEntity deviceList() throws RemoteException {
        deviceTargetingService.selectDeviceList();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
