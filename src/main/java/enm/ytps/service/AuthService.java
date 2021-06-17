package enm.ytps.service;

import enm.ytps.model.AmApiCredential;
import enm.ytps.repository.ApiCredentialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
    private ApiCredentialRepository apiCredentialRepository;
    
    public AuthService(ApiCredentialRepository apiCredentialRepository) {
        this.apiCredentialRepository = apiCredentialRepository;
    }
    
    private AmApiCredential getCredential(long credentialId) {
        Optional<AmApiCredential> credential = apiCredentialRepository.findById(credentialId);
        return credential.orElse(null);
    }
    
    
}
