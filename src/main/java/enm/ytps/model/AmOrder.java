package enm.ytps.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_order")
public class AmOrder {
    
    /* Number(19) */
    @Id
    @EqualsAndHashCode.Include()
    @Column(name="id", unique = true, updatable = false, nullable = false)
    private Long id;
    
    /* VARCHAR(255) */
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    /* VARCHAR(14) */
    @Column(name = "startDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime startDateTime;

    /* VARCHAR(14) */
    @Column(name = "endDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime endDateTime;
    
    /* BOOLEAN(1) */
    private Boolean unlimitedEndDateTime;
    
    /* VARCHAR(50) */
    private String status;

    /* BOOLEAN(1) */
    private Boolean isArchived;
    
    /* VARCHAR(2048) */
    @Column(length = 2048)
    private String notes;
    
    private Integer externalOrderId;
    
    /* VARCHAR(63) */
    @Column(length = 63)
    private String poNumber;
    
    /* VARCHAR(255) */
    @Column(length = 255)
    private String currencyCode;

    /* Number(19) */
    private Long advertiserId;

    /* Number(19) */
    private Long agencyId;

    /* Number(19) */
    private Long creatorId;

    /* Number(19) */
    private Long traffickerId;

    /* Number(19) */
    private Long salespersonId;

    /* Number(19) */
    private Long totalImpressionsDelivered;

    /* Number(19) */
    private Long totalClicksDelivered;

    /* Number(19) */
    private Long totalViewableImpressionsDelivered;

    /* Number(19) */
    private Long totalBudget;
    
    /* VARCHAR() */
    private String lastModifiedByApp;
    
    /* BOOLEAN(1)*/
    private Boolean isProgrammatic;
    
    /* VARCHAR(14) */
    @Column(name = "lastModifiedDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime lastModifiedDateTime;
    
}
