package enm.ytps.service;

import com.google.api.ads.admanager.axis.v202005.Company;
import com.google.api.ads.admanager.axis.v202005.CompanyType;
import enm.ytps.service.ads.GoogleAdsCompanyService;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {
    
    private GoogleAdsCompanyService googleAdsCompanyService;
    
    public AgencyService(GoogleAdsCompanyService googleAdsCompanyService) {
        this.googleAdsCompanyService = googleAdsCompanyService;
    }

    public Company[] getList() {
        return googleAdsCompanyService.getCompanies(null, CompanyType.AGENCY);
    }

    public Company getById(Long advertiserId) {
        return googleAdsCompanyService.getCompanyByIdAndType(advertiserId, CompanyType.AGENCY);
    }

    public Company create(String name) {
        Company company = new Company();
        company.setName(name);
        company.setType(CompanyType.AGENCY);
        return googleAdsCompanyService.create(company);
    }

    public Company update(Long id, String name) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setType(CompanyType.AGENCY);
        return googleAdsCompanyService.update(company);
    }

}
