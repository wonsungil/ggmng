package enm.ytps.controller.api;

import enm.ytps.service.CmsMetaDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cmsMetaData")
@Controller
public class CmsMetaDataController {
    
    private CmsMetaDataService cmsMetaDataService;
    
    public CmsMetaDataController(CmsMetaDataService cmsMetaDataService) {
        this.cmsMetaDataService = cmsMetaDataService;
    }

    /**
     * CmsMetaData Key 리스트 조회
     * @return
     */
    @RequestMapping("/getKeyList")
    public ResponseEntity getKeyList() {
        return new ResponseEntity<>(cmsMetaDataService.getKeyList(), HttpStatus.OK);
    }

    /**
     * CmsMetaData Key에 해당하는 values 조회
     * @param cmsKeyId
     * @return
     */
    @RequestMapping("/getValuesByCmsKeyId/{cmsKeyId}")
    public ResponseEntity getValuesByCmsKeyId(@PathVariable Long cmsKeyId){
        return new ResponseEntity<>(cmsMetaDataService.getValuesByCmsKeyId(cmsKeyId), HttpStatus.OK);
    }
}
