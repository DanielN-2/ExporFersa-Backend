package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.DataConflictException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void validationNewUser(String email, String username){
        if (authRepository.existsByEmail(email)){
            throw new DataConflictException("E-mail ja cadastrado no sistema.");
        }
        if (authRepository.existsByUsername(username)) {
            throw new DataConflictException("Nome de usuário já cadastrado no sistema.");
        }

    }

    public void validationUpdatePassword(boolean matches) {
        if (!matches) {
            throw new DataConflictException("A senha atual informada está incorreta.");
        }
    }

    public AuthRole resolveRole(String email) {
        String domain = email.substring(email.lastIndexOf("@") + 1)
                .toLowerCase(Locale.ROOT);

        return domain.equals("ufersa.edu.br")
                || domain.endsWith(".ufersa.edu.br")
                ? AuthRole.USER
                : AuthRole.GUEST;
    }
}
