package enm.ytps.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_targeting_preset")
public class AmTargetingPreset {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name="id", unique = true, updatable = false, nullable = false)
    private Long id;
    private String name;
    
    @Lob
    private String targetingJsonValue;
    
}
