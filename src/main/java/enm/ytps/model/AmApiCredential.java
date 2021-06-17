package enm.ytps.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@Entity
@Table(name = "am_api_credential")
public class AmApiCredential {
    
    @Id
    private long crdntId;
    private long mediaId;
    private long crdntNetworkCd;
    private String crdntAppNm;
    private String crdntNm;
    private String crdntTp;
    private String crdntPjtId;
    private String crdntPrvKeyId;
    
    @Column(length = 2048)
    private String crdntPrvKey;
    private String crdntClntEmail;
    private String crdntClntId;
    private String crdntAuthUri;
    private String crdntTokenUrl;
    private String crdntAuthProvX509CertUrl;
    private String crdntClntX509CertUrl;
}
