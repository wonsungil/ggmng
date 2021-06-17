package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Company;
import com.google.api.ads.admanager.axis.v202005.Creative;
import com.google.api.ads.admanager.axis.v202005.Order;

import com.google.api.ads.admanager.axis.v202005.VideoRedirectCreative;
import enm.ytps.service.ads.GoogleAdsCreativeService;
import enm.ytps.dto.creative.CreativeListDto;
import enm.ytps.dto.creative.CreativeDto;
import enm.ytps.dto.order.OrderListDto;
import enm.ytps.service.ads.GoogleAdsCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CreativeService {
    
    private GoogleAdsCreativeService googleAdsCreativeService;
    private GoogleAdsCompanyService googleAdsCompanyService;
    
    public CreativeService(GoogleAdsCreativeService googleAdsCreativeService, GoogleAdsCompanyService googleAdsCompanyService) {
        this.googleAdsCreativeService = googleAdsCreativeService;
        this.googleAdsCompanyService = googleAdsCompanyService;
    }

    public List getCreativeList(int page, int perPage, String type) {
        return getCreativeList(page,perPage,type,null);
    }
    
    public List getCreativeList(int page, int perPage, String type, String creativeType) {
        List<CreativeListDto> creativeList = new ArrayList<>();
        String orderBy = "lastModifiedDateTime Desc";

        Creative[] creatives = null;

        if (type == null) {
            creatives = googleAdsCreativeService.getCreatives(orderBy, page, perPage);
        } else if (type.equals("Video")) {
            creatives = googleAdsCreativeService.getVideoCreatives(creativeType, orderBy, page, perPage);
        } else if (type.equals("Image")) {
            creatives = googleAdsCreativeService.getImageCreatives(orderBy, page, perPage);
        }
    	if (creatives == null) {
            return null;
        }
    	
    	CreativeListDto creativeWithAssociative = null;
        Company[] companies = googleAdsCompanyService.getAdvertisersWhereInIdsByCreatives(creatives);

        for (Creative creative : creatives) {
            creativeWithAssociative = new CreativeListDto();
            creativeWithAssociative.setCreative(creative);

            creativeWithAssociative.setAdvertiser(
                    Arrays.stream(companies)
                            .filter( c -> c.getId().equals(creative.getAdvertiserId()))
                            .findAny()
                            .orElse(null));
            creativeList.add(creativeWithAssociative);
        }
        log.info("page = " + page + ", size=" + creativeList.size());
        return creativeList;
    }

    public List getCreativeList(int page) {
        return getCreativeList(page, 10, null);
    }

    public Creative getCreativeById(Long creativeId) {
        
        Creative creative = googleAdsCreativeService.getCreativeById(creativeId);
        
        if(creative instanceof VideoRedirectCreative) {
            creative = (VideoRedirectCreative) creative;
        }
        
        return creative;
    }
    
    public List getImageCreativeList(int page) {
        return getCreativeList(page,20, "Image");
    }
    
    public List getVideoCreativeList(int page, String creativeType) {
        return getCreativeList(page,20, "Video", creativeType);
    }
    
    public int getVideoCreativeSize(String creativeType) {
        return googleAdsCreativeService.getVideoCreativeSize(creativeType);
    }
} 
