package enm.ytps.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_cms_metadata_value")
public class AmCmsMetadataValue {

    /* Number(19) */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long cmsMetadataValueId;
    
    /* VARCHAR(255) */
    @Column(length = 255)
    private String valueName;
    
//    @Column(insertable = false, updatable = false)
//    private AmCmsMetadataKey key;

    /* VARCHAR(20) */
    @Column(length = 20)
    private String status;
    
    
    
}
