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
@Table(name = "am_cms_source")
public class AmCmsContent {

    /* Number(19) */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long id;
    
    /* VARCHAR(255) */
    @Column(length = 255)
    private String displayName;
    
    /* VARCHAR(255) */
    @Column(length = 255)
    private String cmsContentId;
}
