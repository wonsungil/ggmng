package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import com.google.common.collect.Iterables;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class GoogleAdsCompanyService {
    
    @Autowired
    private CompanyServiceInterface amCompanyService;
    
    public Company[] getAdvertisersWhereInIdsByOrders(Order[] orders) {
        
        StringBuilder strWhereAdvertiserIds = new StringBuilder()
                .append("id in (")
                .append(Arrays.stream(orders).map(x->Long.toString(x.getAdvertiserId())).collect(Collectors.joining(",")))
                .append(")");

        CompanyPage page = null;
        try {
            page = amCompanyService.getCompaniesByStatement(new StatementBuilder()
                    .where("type = :type")
                    .where(strWhereAdvertiserIds.toString())
                    .withBindVariableValue("type", CompanyType.ADVERTISER.toString())
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }

        return page.getResults();
    }
    
    public Company[] getAdvertisersWhereInIdsByCreatives(Creative[] creatives) {
        
        StringBuilder strWhereAdvertiserIds = new StringBuilder()
                .append("id in (")
                .append(Arrays.stream(creatives).map(x->Long.toString(x.getAdvertiserId())).collect(Collectors.joining(",")))
                .append(")");

        CompanyPage page = null;
        try {
            page = amCompanyService.getCompaniesByStatement(new StatementBuilder()
                    .where("type = :type")
                    .where(strWhereAdvertiserIds.toString())
                    .withBindVariableValue("type", CompanyType.ADVERTISER.toString())
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }

        return page.getResults();
    }
    
    /**
     * 광고주 ID 지정하여 조회
     * @param advertiserId
     * @return
     */
    public Company getAdvertisersWhereInIdByCreative(Long advertiserId) {

        CompanyPage page = null;
        try {
        	page = amCompanyService.getCompaniesByStatement(new StatementBuilder()
        			.where("type = :type")
        			.withBindVariableValue("type", CompanyType.ADVERTISER.toString())
        			.where("id = :id")
                    .withBindVariableValue("id", advertiserId)
                    .limit(1)
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }

        if (page == null) {
            return null;
        }
        
        return Iterables.getOnlyElement(Arrays.asList(page.getResults()));
    }
    
    
    
    /**
     * Company 등록
     * @param company
     * @return
     */
    public Company create(Company company) {
        Company created = null;
        try {
            created = Iterables.getOnlyElement(Arrays.asList(amCompanyService.createCompanies(new Company[] {company})));
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }
        
        if (created == null) {
            throw new AdMngNetworkException(ErrorMessages.COMPANY_CREATE_FAILED.getCode()
                    , ErrorMessages.COMPANY_CREATE_FAILED.getMessage());
        }
        
        return created;
    }

    /**
     * Company 수정
     * @param company
     * @return
     */
    public Company update(Company company) {
        Company updated = null;
        try {
            updated = Iterables.getOnlyElement(Arrays.asList(amCompanyService.updateCompanies(new Company[]{company})));
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }
        return updated;
    }

    public Company getCompanyByIdAndType(Long id, CompanyType type) {
        CompanyPage page = null;

        try {
            page = amCompanyService.getCompaniesByStatement(new StatementBuilder()
                    .where("id = :id")
                    .where("type = :type")
                    .withBindVariableValue("id", id)
                    .withBindVariableValue("type", type.toString())
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }
        
        if (page == null) {
            throw new AdMngNetworkException(ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return Iterables.getOnlyElement(Arrays.asList(page.getResults()));
    }

    public Company[] getCompanies(String name, CompanyType type) {

        CompanyPage page = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .limit(30)
                .orderBy("id DESC");

        if (name != null) {
            statementBuilder.where("name like %:name%").withBindVariableValue("name", name);
        }

        if (type != null) {
            statementBuilder.where("type = :type").withBindVariableValue("type", type.toString());
        }

        try {
            page = amCompanyService.getCompaniesByStatement(statementBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode(),
                    ErrorMessages.CONNECTION_ERROR.getMessage(),
                    e.getCause());
        }
        
        if (page == null) {
            throw new AdMngNetworkException(ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return page.getResults();
    }
}
