package enm.ytps.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Entity
@Builder
@Table(name = "am_user")
public class AmUser {
    
    /* Number(19) */
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(length = 128)
    private String name;
    
    @Column(length = 128)
    private String email;

    /* Number(19) */
    private Long roleId;

    @Column(length = 255)
    private String roleName;

    /* BOOLEAN(1) */
    private Boolean isActive;

    /* BOOLEAN(1) */
    private Boolean isEmailNotificationAllowed;

    @Column(length = 255)
    private String externalId;
    
    /* BOOLEAN(1) */
    private Boolean isServiceAccount;

    @Column(length = 255)
    private String orderUiLocaltimeZoneId;
}
