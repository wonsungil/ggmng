package enm.ytps.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Entity
@Builder
@Table(name = "am_user_team_association")
public class AmUserTeamAssociation {
    
    /* Number(19) */
    @EqualsAndHashCode.Include()
    @EmbeddedId
    private AmUserTeamAssociationId amUserTeamAssociationId;
    
    /* Varchar(20) */
    @Column(length = 20, nullable = true)
    private String overridenTeamAccessType;

    /* Varchar(20) */
    @Column(length = 20, nullable = true)
    private String defaultTeamAccessType;
    
}
