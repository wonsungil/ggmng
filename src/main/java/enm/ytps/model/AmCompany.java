package enm.ytps.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Entity
@Builder
@Table(name = "am_company")
public class AmCompany {

    /* Number(19) */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long id;
    
    /* VARCHAR(127) */
    @Column(length = 127)
    private String name;
    
    /* VARCHAR(100) */
    @Column(length = 100)
    private String type;
    
    /* VARCHAR(2048) */
    @Column(length = 2048)
    private String address;
    
    /* VARCHAR(128) */
    @Column(length = 128)
    private String email;

    /* VARCHAR(63) */
    @Column(length = 63)
    private String faxPhone;
    
    /* VARCHAR(63) */
    @Column(length = 63)
    private String primaryPhone;
    
    /* VARCHAR(255) */
    @Column(length = 255)
    private String externalId;
    
    /* VARCHAR(1024) */
    @Column(length = 1024)
    private String comment;
    
    /* VARCHAR(50) */
    @Column(length = 50)
    private String creditStatus;

    /* Number(19) */
    private Long primaryContactId;

    /* Number(19) */
    private Long thirdPartyCompanyId;

    /* VARCHAR(14) */
    @Column(name = "lastModifiedDateTime", columnDefinition = "TIMESTAMP")
    private String lastModifiedDateTime;
}
