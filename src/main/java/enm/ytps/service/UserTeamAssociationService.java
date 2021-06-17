package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.UserTeamAssociation;
import enm.ytps.service.ads.GoogleUserTeamAssociationService;
import org.springframework.stereotype.Service;

@Service
public class UserTeamAssociationService {
    
    private GoogleUserTeamAssociationService googleUserTeamAssociationService;
    
    public UserTeamAssociationService(GoogleUserTeamAssociationService googleUserTeamAssociationService) {
        this.googleUserTeamAssociationService = googleUserTeamAssociationService;
    }
    
    public UserTeamAssociation[] getUserTeamAssociations(String orderBy, int page, int perPage) {
        return googleUserTeamAssociationService.getUserTeamAssociations(orderBy, page, perPage);
    } 
    
    public UserTeamAssociation[] getUserTeamAssociations(int page, int perPage) {
        return googleUserTeamAssociationService.getUserTeamAssociations(null, page, perPage);
    }

}
