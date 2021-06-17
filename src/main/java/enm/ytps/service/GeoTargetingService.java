package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.*;
import enm.ytps.service.ads.GoogleAdsPqlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoTargetingService {
    
    private GoogleAdsPqlService googleAdsPqlService;

    public GeoTargetingService(GoogleAdsPqlService googleAdsPqlService) {
        this.googleAdsPqlService = googleAdsPqlService;
    }

    /**
     * Geo Targeting ( 대한민국 ) 
     * @return
     */
    public GeoTargeting getKorGeoTarget() {
//        Row geoRow = googlePqlService.getKorGetTarget().getRows(0);
//        Location location = new Location();
//        location.setId(Long.parseLong(((NumberValue)geoRow.getValues(0)).getValue()));
//        location.setType("country");
//        location.setDisplayName(((TextValue)geoRow.getValues(1)).getValue());
//
//        GeoTargeting geoTargeting = new GeoTargeting();
//        geoTargeting.setTargetedLocations(new Location[] {location});
        
        Location kor = new Location(2410L, "country", null, "South Korea");
        GeoTargeting geoTargeting = new GeoTargeting();
        geoTargeting.setTargetedLocations(new Location[]{kor});
        
        return geoTargeting;
    }
    
    public GeoTargeting getGeoTargetList() {
        ResultSet resultSet = googleAdsPqlService.getGeoTargetList();
        
        List<Location> locationList = new ArrayList<>(); 
        for (Row row : resultSet.getRows()) {
            locationList.add(new Location(
                    Long.parseLong(((NumberValue)row.getValues(0)).getValue()), 
                    "country", 
                    null, 
                    ((TextValue)row.getValues(1)).getValue()));
        }
        GeoTargeting geoTargeting = new GeoTargeting();
        geoTargeting.setTargetedLocations((Location[]) locationList.toArray());
        return geoTargeting;
    }
}   
