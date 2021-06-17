package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Company;
import com.google.api.ads.admanager.axis.v202005.CompanyType;
import enm.ytps.service.ads.GoogleAdsCompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private GoogleAdsCompanyService googleCompanyService;
    
    public CompanyService(GoogleAdsCompanyService googleCompanyService) {
        this.googleCompanyService = googleCompanyService;
    }

    public Company[] getCompanies(String name, String Type) {
        return googleCompanyService.getCompanies(null, null);
    }
    
    public Company[] getCompanies() { 
        return googleCompanyService.getCompanies(null, null);
    }
    
    public Company[] getCompanies(String name) {
        return googleCompanyService.getCompanies(name, null);
    }

    public Company[] getCompanies(CompanyType type) {
        return googleCompanyService.getCompanies(null, type);
    }
    
    public Company create(Company company) {
        return googleCompanyService.create(company);
    }
    
    public Company update(Company company) {
        return googleCompanyService.update(company);
        
    }
}
