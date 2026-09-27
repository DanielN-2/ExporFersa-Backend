package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public Auth toEntity(UserCreateDTO dto, String encryptedPassword) {
        return new Auth(dto.username(), dto.email(), encryptedPassword, dto.role());
    }

    public UserResponseDTO toResponseDTO(Auth auth) {
        return new UserResponseDTO(
                auth.getId(),
                auth.getUsername(),
                auth.getEmail(),
                auth.getRole()
        );
    }
}
