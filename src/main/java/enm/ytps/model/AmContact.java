package enm.ytps.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(name = "am_contact")
public class AmContact {

    /* Number(19) */
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;
    
    /* VARCHAR(127) */
    @Column(length = 127)
    private String name;
    
    /* NUMBER(19) */
    private Long companyId;
    
    /* VARCHAR(50) */
    @Column(length = 50)
    private String status;

    /* VARCHAR(2048) */
    @Column(length = 2048)
    private String address;

    /* VARCHAR(63) */
    @Column(length = 63)
    private String cellPhone;

    /* VARCHAR(1024) */
    @Column(length = 1024)
    private String comment;

    /* VARCHAR(128) */
    @Column(length = 128)
    private String email;

    /* VARCHAR(63) */
    @Column(length = 63)
    private String faxPhone;

    /* VARCHAR(50) */
    @Column(length = 1024)
    private String title;

    /* VARCHAR(63) */
    @Column(length = 63)
    private String workPhone;
}
