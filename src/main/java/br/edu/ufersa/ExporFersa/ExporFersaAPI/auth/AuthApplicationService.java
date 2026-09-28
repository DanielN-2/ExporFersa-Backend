package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserPasswordUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthApplicationService {

    private final AuthRepository authRepository;
    private final AuthService domainService;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public AuthApplicationService(AuthRepository authRepository, AuthService domainService, AuthMapper mapper, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.domainService = domainService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO register(UserCreateDTO dto) {
        domainService.validationNewUser(dto.email());

        String encryptedPassword = passwordEncoder.encode(dto.senha());
        Auth auth = mapper.toEntity(dto, encryptedPassword);

        return mapper.toResponseDTO(authRepository.save(auth));
    }

    public List<UserResponseDTO> listUsers() {
        return authRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public UserResponseDTO searchById(UUID id) {
        Auth auth = getAuthOrThrow(id);
        return mapper.toResponseDTO(auth);
    }

    @Transactional
    public UserResponseDTO updateUser(UUID id, UserUpdateDTO dto) {
        Auth auth = getAuthOrThrow(id);
        auth.setUsername(dto.username());
        return mapper.toResponseDTO(authRepository.save(auth));
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!authRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }
        authRepository.deleteById(id);
    }

    @Transactional
    public void updatePassword(UUID id, UserPasswordUpdateDTO dto) {
        Auth auth = getAuthOrThrow(id);

        boolean matches = passwordEncoder.matches(dto.oldPassword(), auth.getPassword());
        domainService.validationUpdatePassword(matches);

        auth.setPassword(passwordEncoder.encode(dto.newPassword()));
        authRepository.save(auth);
    }

    private Auth getAuthOrThrow(UUID id) {
        return authRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }
}
