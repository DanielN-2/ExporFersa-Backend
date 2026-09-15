package br.edu.ufersa.ExporFersa.ExporFersaAPI.usuario.dtos;

import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.usuario.entity.Usuario;

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
