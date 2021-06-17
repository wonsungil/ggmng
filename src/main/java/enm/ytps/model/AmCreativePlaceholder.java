package enm.ytps.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_creative_placeholder")
public class AmCreativePlaceholder {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long id;
    private Long lineItemId;
    private Integer width;
    private Integer height;
    private boolean isAspectRatio;
    private Integer expectedCreativeCount;
    private String creativeSizeType;
    private String targetingName;
    private boolean isAmpOnly; 
}
