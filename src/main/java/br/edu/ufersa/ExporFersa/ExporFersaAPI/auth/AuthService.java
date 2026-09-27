package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void validationNewUser(String email){
        if (authRepository.existsByEmail(email)){
            throw new IllegalArgumentException("E-mail ja cadastrado no sistema.");
        }
    }

    public void validationUpdatePassword(boolean matches) {
        if (!matches) {
            throw new IllegalArgumentException("A senha atual informada está incorreta.");
        }
    }
}
