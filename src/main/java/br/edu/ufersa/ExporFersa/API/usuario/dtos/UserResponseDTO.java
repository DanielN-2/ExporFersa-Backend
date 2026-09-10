package br.edu.ufersa.ExporFersa.API.usuario.dtos;

import br.edu.ufersa.ExporFersa.domain.usuario.Usuario;

import java.util.UUID;

public record UserResponseDTO (
        UUID id,
        String email,
        String username

        // FAZER A LISTAGEM DE PROJETOS E PROJETOS COM LIKES FUTURAMENTE
) {
    public UserResponseDTO(Usuario user) {
        this(user.getId(), user.getEmail(), user.getUsername());
    }
}
