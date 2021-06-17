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
@Table(name = "am_cms_metadata_key")
public class AmCmsMetadataKey {

    /* Number(19) */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long id;
    
    /* VARCHAR(255) */
    @Column(length = 255)
    private String name;

    /* VARCHAR(20) */
    @Column(length = 20)
    private String status;
}
