package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthDetailsServiceImpl implements UserDetailsService {
    private final AuthRepository authRepository;
    AuthDetailsServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
