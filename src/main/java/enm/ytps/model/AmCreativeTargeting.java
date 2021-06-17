package enm.ytps.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_creative_targeting")
public class AmCreativeTargeting {
    
    @Id
    private Long id;
    
    private Long lineItemId;
    
    @Lob
    private String targetingJsonValue;
}
