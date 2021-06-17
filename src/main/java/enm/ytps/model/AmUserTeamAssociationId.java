package enm.ytps.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Embeddable
@Builder
public class AmUserTeamAssociationId implements Serializable {
    
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "user_id")
    private Long userId;
    
}
