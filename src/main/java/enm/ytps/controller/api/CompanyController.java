package enm.ytps.controller.api;

import com.google.api.ads.admanager.axis.v202005.CompanyType;
import enm.ytps.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/company")
@Controller
public class CompanyController {
    
    private CompanyService companyService;
    
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listByName/{name}", method = RequestMethod.GET)
    public ResponseEntity listByName(@PathVariable String name) {
        return new ResponseEntity<>(companyService.getCompanies(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/listByType/{type}", method = RequestMethod.GET)
    public ResponseEntity listByType(@PathVariable String type) {
        return new ResponseEntity<>(companyService.getCompanies(CompanyType.fromString(type)), HttpStatus.OK);
    }
}
