package enm.ytps.config;

import com.google.api.ads.admanager.axis.factory.AdManagerServices;
import com.google.api.ads.admanager.axis.v202005.*;
import com.google.api.ads.admanager.lib.client.AdManagerSession;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.conf.ConfigurationLoadException;
import com.google.api.ads.common.lib.exception.ValidationException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static com.google.api.ads.common.lib.utils.Builder.DEFAULT_CONFIGURATION_FILENAME;

@Configuration
public class AdManagerConfig {
    /**
     * TODO : Dynamic Bean Registry 처리 필요.
     */
    
    @Bean
    public AdManagerServices adManagerServices() {
        return new AdManagerServices();
    }
    
    @Bean
    public AdManagerSession adManagerSession() {
        AdManagerSession session = null;
        
        Resource resource = new ClassPathResource("auth.json");
        try {
//            GoogleCredential credential = GoogleCredential.fromStream(jsonKeyFile.getInputStream(),
            GoogleCredential credential = GoogleCredential.fromStream(resource.getInputStream(),
                    new NetHttpTransport(),                    
                    new JacksonFactory()).createScoped(Lists.newArrayList(OfflineCredentials.Api.AD_MANAGER.getScope()));
            
            session = new AdManagerSession.Builder()
                    .fromFile()
                    .withOAuth2Credential(credential)
                    .build();
            
        } catch (ConfigurationLoadException cle) {
            System.err.printf("Failed to load configuration from the %s file. Exception: %s%n",
                    DEFAULT_CONFIGURATION_FILENAME, cle);
        } catch (ValidationException ve) {
            System.err.printf("Invalid configuration in the %s file. Exception: %s%n",
                    DEFAULT_CONFIGURATION_FILENAME, ve);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Bean(name = "amOrderService")
    public OrderServiceInterface orderService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, OrderServiceInterface.class);
    }

    @Bean(name = "amCompanyService")
    public CompanyServiceInterface companyService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, CompanyServiceInterface.class);
    }

    @Bean(name = "amLineItemService")
    public LineItemServiceInterface lineItemService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, LineItemServiceInterface.class);
    }

    @Bean(name = "amLICAService")
    public LineItemCreativeAssociationServiceInterface lineItemCreativeAssociationService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, LineItemCreativeAssociationServiceInterface.class);
    }

    @Bean(name = "amCreativeService")
    public CreativeServiceInterface creativeService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, CreativeServiceInterface.class);
    }

    @Bean(name = "amInventoryService")
    public InventoryServiceInterface inventoryService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, InventoryServiceInterface.class);
    }

    @Bean(name = "amNetworkService")
    public NetworkServiceInterface networkService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, NetworkServiceInterface.class);
    }

    @Bean(name = "amCmsMetaDataService")
    public CmsMetadataServiceInterface cmsMetaDataService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, CmsMetadataServiceInterface.class);
    }

    @Bean(name = "amPqlService")
    public PublisherQueryLanguageServiceInterface publisherQueryLanguageService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, PublisherQueryLanguageServiceInterface.class);
    }

    @Bean(name = "amCustomTargetingService")
    public CustomTargetingServiceInterface customTargetingService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, CustomTargetingServiceInterface.class);
    }

    @Bean(name = "amContentService")
    public ContentServiceInterface contentService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, ContentServiceInterface.class);
    }

    @Bean(name = "amContentBundle")
    public ContentBundleServiceInterface contentBundleService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, ContentBundleServiceInterface.class);
    }

    @Bean(name = "amTargetingPresetService")
    public TargetingPresetServiceInterface targetingPresetService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, TargetingPresetServiceInterface.class);
    }

    @Bean(name = "amPlacementService")
    public PlacementServiceInterface placementService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, PlacementServiceInterface.class);
    }
    
    @Bean(name = "amReportService")
    public ReportServiceInterface reportService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, ReportServiceInterface.class);
    }
    
    @Bean(name = "amUserService")
    public UserServiceInterface userService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, UserServiceInterface.class);
    }
    
    @Bean(name = "adTeamService")
    public TeamServiceInterface teamService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, TeamServiceInterface.class);
    }
    
    @Bean(name = "amContactService")
    public ContactServiceInterface contactService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, ContactServiceInterface.class);
    }
    
    @Bean(name = "amLabelService")
    public LabelServiceInterface labelService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, LabelServiceInterface.class);
    }
    
    @Bean(name = "amUserTeamAssociationService")
    public UserTeamAssociationServiceInterface userTeamAssociationService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, UserTeamAssociationServiceInterface.class);
    }

    @Bean(name = "amCustomFieldService")
    public CustomFieldServiceInterface customFieldService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, CustomFieldServiceInterface.class);
    }
    @Bean(name = "amForecastService")
    public ForecastServiceInterface forecastService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, ForecastServiceInterface.class);
    }

    @Bean(name = "amAudienceSegmentService")
    public AudienceSegmentServiceInterface audienceSegmentServiceInterface(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, AudienceSegmentServiceInterface.class);
    }

    @Bean(name = "amCreativeSetService")
    public CreativeSetServiceInterface creativeSetService(AdManagerServices adManagerServices, AdManagerSession adManagerSession) {
        return adManagerServices.get(adManagerSession, CreativeSetServiceInterface.class);
    }
}
