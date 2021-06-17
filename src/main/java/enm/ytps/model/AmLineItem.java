package enm.ytps.model;

import com.google.api.ads.admanager.axis.v202005.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_line_item")
public class AmLineItem {
    
    @Id
    private long id;
    private long orderId;
    private String name;
    private String externalId;
    private String orderName;
    private LocalDateTime startDateTime;
    private String startDateTimeType; /* StartDateTimeType */
    private LocalDateTime endDateTime;
    private int autoExtensionDays;
    private boolean unlimitedEndDateTime;
    private String creativeRotationType; /* CreativeRotationType */
    private String deliveryRateType; /* DeliveryRateType */
    private String deliveryForecastSource; /* DeliveryForecastSource */
    private String roadblockingType; /* RoadblockingType */
    private String skippableAdType; /* SkippableAdType */
    private String lineItemType; /* LineItemType */
    private int priority; /* LineItemType.SPONSORSHIP	4 (2, 5) LineItemType.STANDARD	8 (6, 10)*/
    private String costPerUnitCurrencyCode; /* Money */
    private Long costPerUnitAmount;
    private String valueCostPerUnitCurrencyCode;
    private Long valueCostPerUnitAmount;
    private String costType;
    private String lineItemDiscountType;
    private double discount;
    private long contractedUnitBought;
    private String environmentType;
    private String companionDeliveryOption;
    private boolean allowOverbook;
    private boolean skipInventoryCheck;
    private boolean skipCrossSellingRuleWarningChecks;
    private boolean reserveAtCreation;
    private Long impressionsDelivered; /* Stat */
    private Long clicksDelivered; /* Stat */
    private Long videoCompletionsDelivered; /* Stat */
    private Long videoStartsDelivered; /* Stat */
    private Long viewableImpressionsDelivered; /* Stat */
    private Double expectedDeliveryPercentage;
    private Double actualDeliveryPercentage;
    private String budgetCurrencyCode;
    private Long budgetAmount;
    private String status;
    private String reservationStatus;
    private boolean isArchived;
    private boolean disableSameAdvertiserCompetitiveExclusion;
    private String lastModifiedByApp;
    private String notes;
    private String competitiveConstraintScope;
    private LocalDateTime lastModifiedDateTime;
    private LocalDateTime creationDateTime;
    private boolean isPrioritizedPreferredDealsEnabled;
    private boolean isMissingCreatives;
    private String programmaticCreativeSource;
    private long videoMaxDuration;
    private String primaryGoalType; /* primary goal */
    private String primaryGoalUnitType; /* primary goal */
    private Long primaryGoalUnits; /* primary goal */
    private String userConsentEligibility;
    private String childContentEligibility;
    private String customVastExtension;
    
    @Lob
    private String targetingJsonValue;
    
    @Transient
    private List<AmCreativeTargeting> creativeTargetings;
    @Transient
    private List<AmFrequencyCap> frequencyCaps;
    @Transient
    private List<AmCreativePlaceholder> creativePlaceholders;
//    private CreativePlaceholder creativePlaceholder;
//    private Goal primaryGoal;
//    private Stats stats;
//    private BaseCustomFieldValue[] customFieldValues;
//    private LineItemActivityAssociation[] lineItemActivityAssociations;
//    private AllowedFormats[] allowedFormats;
//    private CompanionDeliveryOption companionDeliveryOption;
//    private EnvironmentType environmentType;
//    private StartDateTimeType startDateTimeType;
//    private Goal[] secondaryGoals;
//    private GrpSettings grpSettings;
//    private LineItemDealInfoDto lineItemDealInfoDto;
//    private long viewabilityProviderCompanyId;
//    private UserConsentEligibility userConsentEligibility;
//    private ChildContentEligibility childContentEligibility;
//    private AppliedLabel[] appliedLabels;
//    private AppliedLabel[] effectiveAppliedLabels;
//    private DeliveryIndicator deliveryIndicator;
//    private DeliveryData deliveryData;
//    private Money budget;
//    private ComputedStatus status;
//    private String webPropertyCode;
//    private SetTopBoxInfo setTopBoxInfo;
//    private CompetitiveConstraintScope competitiveConstraintScope;
//    private ProgrammaticCreativeSource programmaticCreativeSource;
//    private ThirdPartyMeasurementSettings thirdPartyMeasurementSettings;
}
