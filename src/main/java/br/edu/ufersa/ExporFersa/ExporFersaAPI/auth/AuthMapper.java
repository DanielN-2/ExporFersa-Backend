package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    @Mapping(target = "password", source = "encodePassword")
    void updateUserPassword(String encodePassword, @MappingTarget Auth user);

    void updateUserFromDTO(UserUpdateDTO dto, @MappingTarget Auth user);

    default Auth toEntity(UserCreateDTO dto, String encryptedPassword) {
        return new Auth(dto.username(), dto.email(), encryptedPassword, dto.role());
    }

    default UserResponseDTO toResponseDTO(Auth auth) {
        return new UserResponseDTO(
                auth.getId(),
                auth.getUsername(),
                auth.getEmail(),
                auth.getRole()
        );
    }
}
