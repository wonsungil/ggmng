package enm.ytps.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_content")
public class AmContent {
    
    /* Number(19) */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long id;
    
    /* VARCHAR(1024) */
    @Column(length = 1024)
    private String name;
    
    /* VARCHAR(30) */
    @Column(length = 30)
    private String status;

    /* VARCHAR(10) */
    @Column(length = 10)
    private String statusDefinedBy;
    
    /* VARCHAR(20) */
    @Column(length = 20)
    private String hlsIngestStatus;
    
//    private String[] hlsIngestErrors;

    @Column(name = "lastHlsIngestDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime lastHlsIngestDateTime;

    /* VARCHAR(20) */
    private String dashIngestStatus;
    
//    private String[] dashIngestErrors;

    @Column(name = "lastDashIngestDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime lastDashIngestDateTime;

    @Column(name = "importDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime importDateTime;

    @Column(name = "lastModifiedDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime lastModifiedDateTime;
    
    @ManyToMany
    @JoinTable(name = "am_content_cms_source",
        joinColumns = @JoinColumn(name = "content_id"),
        inverseJoinColumns = @JoinColumn(name = "cms_source_id"))
    private Set<AmCmsContent> cmsSources;

    @ManyToMany
    @JoinTable(name = "am_content_content_bundle",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "content_bundle_id"))
    private Set<AmContentBundle> contentBundles;

    @ManyToMany
    @JoinTable(name = "am_content_cms_metadata_value",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "cms_metadata_value_id"))
    private Set<AmCmsMetadataValue> cmsMetaDataValues;

    /* Number(19) */
    private Long duration;
}
