package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.AuthRole;

public record UserResponseDTO (
        UUID id,
        String email,
        String username,
        AuthRole role

        // FAZER A LISTAGEM DE PROJETOS E PROJETOS COM LIKES FUTURAMENTE
) {
    public UserResponseDTO(Auth auth) {
        this(auth.getId(), auth.getEmail(), auth.getUsername(), auth.getRole());
    }
}
