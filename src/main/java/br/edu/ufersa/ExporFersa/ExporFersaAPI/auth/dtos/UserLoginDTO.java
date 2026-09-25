package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.UserRole;

public record UserLoginDTO (
        String username,
        String senha,
        UserRole role
) {
    public UserLoginDTO {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("O usuario é obrigatório!");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória!");
        }
        if (role == null) {
            throw new IllegalArgumentException("O papel é obrigatório!");
        }
    }
}