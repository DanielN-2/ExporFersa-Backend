package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.*;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.EntityNotFoundException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.ImageData;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.services.FileService;
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
    private final FileService fileService;

    public AuthApplicationService(AuthRepository authRepository, AuthService domainService, AuthMapper mapper, PasswordEncoder passwordEncoder, FileService fileService) {
        this.authRepository = authRepository;
        this.domainService = domainService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

    @Transactional
    public UserResponseDTO register(UserCreateDTO dto) {
        domainService.validationNewUser(dto.email(), dto.username());

        String encryptedPassword = passwordEncoder.encode(dto.password());

        AuthRole role = domainService.resolveRole(dto.email());

        Auth auth = mapper.toEntity(dto, encryptedPassword, role);

        return mapper.toResponseDTO(authRepository.save(auth));
    }
    @Transactional
    public UserResponseDTO registerAdmin(AdminCreateDTO dto) {
        domainService.validationNewUser(dto.email(), dto.username());

        String encryptedPassword = passwordEncoder.encode(dto.password());

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
    public UserResponseDTO getMe(Auth user) {
        return new UserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(UUID id, UserUpdateDTO dto) {
        Auth auth = getAuthOrThrow(id);

        mapper.updateUserFromDTO(dto, auth);

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

        String encodePassword = passwordEncoder.encode(dto.newPassword());
        mapper.updateUserPassword(encodePassword, auth);

        authRepository.save(auth);
    }

    @Transactional
    public UserResponseDTO addImage(UUID id, UserAddImageDTO dto) {
        Auth auth = getAuthOrThrow(id);

        if (auth.getImageURL() != null) {
            fileService.deleteImage(auth.getImageURL());
        }

        ImageData imageData = fileService.saveImage("usuarios", auth.getName(), dto.image());
        auth.setImageURL(imageData.imageURL());

        authRepository.save(auth);
        return mapper.toResponseDTO(auth);
    }

    @Transactional
    public UserResponseDTO deleteImage(UUID id) {
        Auth auth = getAuthOrThrow(id);

        if (auth.getImageURL() == null) {
            throw new InvalidOperationException("O usuário não possui uma imagem associada.");
        }

        if (fileService.existsImage(auth.getImageURL())) {
            fileService.deleteImage(auth.getImageURL());
        }

        auth.setImageURL(null);
        authRepository.save(auth);

        return mapper.toResponseDTO(auth);
    }

    private Auth getAuthOrThrow(UUID id) {
        return authRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }
}
