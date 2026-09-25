package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;

public record UserResponseDTO (
        UUID id,
        String email,
        String username

        // FAZER A LISTAGEM DE PROJETOS E PROJETOS COM LIKES FUTURAMENTE
) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getEmail(), user.getUsername());
    }
}
