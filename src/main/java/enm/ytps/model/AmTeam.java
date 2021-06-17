package enm.ytps.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Entity
@Builder
@Table(name = "am_team")
public class AmTeam {
    
    /* Number(19) */
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    /* VARCHAR(106) */
    private String name;
    
    /* VARCHAR(255) */
    private String description;
    
    /* VARCHAR(100) */
    private String status;
    
    /* BOOLEAN(1) */
    private Boolean hasAllCompanies;

    /* BOOLEAN(1) */
    private Boolean hasAllInventory;

    /* VARCHAR(20) */
    @Column(length = 128)
    private String teamAccessType;
    
}
