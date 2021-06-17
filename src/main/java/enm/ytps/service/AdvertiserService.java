package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Company;
import com.google.api.ads.admanager.axis.v202005.CompanyType;
import enm.ytps.service.ads.GoogleAdsCompanyService;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserService {
    
    private GoogleAdsCompanyService googleAdsCompanyService;
    
    public AdvertiserService(GoogleAdsCompanyService googleAdsCompanyService) {
        this.googleAdsCompanyService = googleAdsCompanyService;
    }
    
    public Company[] getList() {
        return googleAdsCompanyService.getCompanies(null, CompanyType.ADVERTISER);
    }
    
    public Company getById(Long advertiserId) {
        return googleAdsCompanyService.getCompanyByIdAndType(advertiserId, CompanyType.ADVERTISER
        );   
    }
    
    public Company create(String name) {
        Company company = new Company();
        company.setName(name);
        company.setType(CompanyType.ADVERTISER);
        return googleAdsCompanyService.create(company);
    }
    
    public Company update(Long id, String name) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        return googleAdsCompanyService.update(company);
    }
    
}
