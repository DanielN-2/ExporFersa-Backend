package br.edu.ufersa.ExporFersa.ExporFersaAPI.api.usuario.dtos;

public record UserLoginDTO (
        String username,
        String senha
) {
    public UserLoginDTO {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("O usuario e obrigatorio!");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha e obrigatoria!");
        }
    }
}
