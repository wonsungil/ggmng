package enm.ytps.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@Entity
@Table(name = "am_media")
public class AmMedia {
    @Id
    private long mediaId;
    private String mediaName;
}
