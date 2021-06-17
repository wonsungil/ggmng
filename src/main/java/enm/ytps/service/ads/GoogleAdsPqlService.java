package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.PublisherQueryLanguageServiceInterface;
import com.google.api.ads.admanager.axis.v202005.ResultSet;
import enm.ytps.exception.AdMngNetworkException;
import enm.ytps.exception.ErrorMessages;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class GoogleAdsPqlService {
    
    private PublisherQueryLanguageServiceInterface amPqlService;

    public GoogleAdsPqlService(PublisherQueryLanguageServiceInterface amPqlService) {
        this.amPqlService = amPqlService;
    }

    public ResultSet getGeoTargetList(Long geoId) {
        ResultSet result = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .select("Id, Name, CanonicalParentId, ParentIds, CountryCode")
                .from("Geo_Target")
                .orderBy("Id DESC")
                .limit(30);
        
        if (geoId != null) {
            statementBuilder.where("id = :id").withBindVariableValue("id", geoId);   
        }
        
        try {
            result = amPqlService.select(statementBuilder.toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (result == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return result;
    }
    
    public ResultSet getGeoTargetList() {
        return getGeoTargetList(null);
    }
    
    
    public ResultSet getKorGeoTarget() {
        ResultSet result = null;
        try {
            result = amPqlService.select(new StatementBuilder()
                    .select("Id, Name, CanonicalParentId, ParentIds, CountryCode")
                    .from("Geo_Target")
                    .where("id = :id")
                    .limit(1)
                    .withBindVariableValue("id", 2410)
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (result == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }
        
        return result;
    }

    public ResultSet getDeviceList() {
        ResultSet result = null;
        try {
            result = amPqlService.select(new StatementBuilder()
                    .select("Id, DeviceCategoryName")
                    .from("Device_Category")
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (result == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return result;
    }

    public ResultSet getOsList() {
        ResultSet result = null;
        try {
            result = amPqlService.select(new StatementBuilder()
                    .select("Id, OperatingSystemName")
                    .from("Operating_System")
                    .toStatement());
        } catch (RemoteException e) {
            throw new AdMngNetworkException(
                    ErrorMessages.CONNECTION_ERROR.getCode()
                    , ErrorMessages.CONNECTION_ERROR.getMessage()
                    , e.getCause());
        }

        if (result == null) {
            throw new AdMngNetworkException(
                    ErrorMessages.INVALID_API_RESPONSE_ERROR.getCode()
                    , ErrorMessages.INVALID_API_RESPONSE_ERROR.getMessage());
        }

        return result;
    }
}

