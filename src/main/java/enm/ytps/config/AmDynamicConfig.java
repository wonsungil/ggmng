package enm.ytps.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.PemReader;
import com.google.api.client.util.SecurityUtils;
import enm.ytps.model.AmApiCredential;
import enm.ytps.repository.ApiCredentialRepository;
import enm.ytps.repository.MediaRepository;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class AmDynamicConfig {
    
    private GenericApplicationContext genericApplicationContext;
    private ApiCredentialRepository apiCredentialRepository;
    private MediaRepository mediaRepository;

    public AmDynamicConfig(GenericApplicationContext genericApplicationContext,
                           ApiCredentialRepository apiCredentialRepository, MediaRepository mediaRepository) {
        this.genericApplicationContext = genericApplicationContext;
        this.mediaRepository = mediaRepository;
        this.apiCredentialRepository = apiCredentialRepository;
    }

    public PrivateKey privateKeyFromPkcs8(String privateKeyPem) throws IOException {
        Reader reader = new StringReader(privateKeyPem);
        PemReader.Section section = PemReader.readFirstSectionAndClose(reader, "PRIVATE KEY");
        if (section == null) {
            throw new IOException("Invalid PKCS8 data");
        }
        byte[] bytes = section.getBase64DecodedBytes();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        Exception unexceptedException = null;
        try {
            KeyFactory keyFactory = SecurityUtils.getRsaKeyFactory();
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {
            unexceptedException = e;
        } catch (InvalidKeySpecException e) {
            unexceptedException = e;
        }
        throw new IOException("Unexcepted exception reading PKCS data" + unexceptedException.getMessage());
    }
    
    public void registerAdManagerBeans() throws IOException {

        Collection<String> emptyScopes = Collections.emptyList();

        for (AmApiCredential credential : apiCredentialRepository.findAll()) {

            PrivateKey privateKey = privateKeyFromPkcs8(credential.getCrdntPrvKey());

            GoogleCredential googleCredential = new GoogleCredential.Builder()
                    .setServiceAccountId(credential.getCrdntClntEmail())
                    .setServiceAccountScopes(emptyScopes)
                    .setServiceAccountPrivateKey(privateKey)
                    .setServiceAccountPrivateKeyId(credential.getCrdntPrvKeyId())
                    .setTokenServerEncodedUrl(credential.getCrdntTokenUrl())
                    .setServiceAccountProjectId(credential.getCrdntPjtId())
                    .setTransport(new NetHttpTransport())
                    .setJsonFactory(new JacksonFactory())
                    .build();
        }
    }
}
