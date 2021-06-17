package enm.ytps.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_content_bundle")
public class AmContentBundle {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include()
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(length = 20)
    private String status;

    @Column(name = "lastModifiedDateTime", columnDefinition = "TIMESTAMP", length = 14)
    private LocalDateTime lastModifiedDateTime;
    
    
}
