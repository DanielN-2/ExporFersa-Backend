package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserCreateDTO;

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(UserCreateDTO dto) {
        if(userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }
        String encryptedPassword = passwordEncoder.encode(dto.senha());
        User user = new User(dto.username(), dto.email(), encryptedPassword, dto.role());
        userRepository.save(user);
    }
}
