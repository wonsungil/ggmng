package enm.ytps.model;

import lombok.*;

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
@Table(name = "am_image_creative")
public class AmImageCreative {
    
    @Id
    private Long id;
    
    private Long advertiserId;
    
    private String name;
    
    private Integer width;
    
    private Integer height;
    
    private boolean isAspectRatio;
    
    private String previewUrl;
    
    private LocalDateTime lastModifiedDateTime;
    
    private String thirdPartyDataDeclaration;
    
}
