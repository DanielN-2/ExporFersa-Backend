package br.edu.ufersa.ExporFersa.API.usuario.dtos;

import br.edu.ufersa.ExporFersa.domain.projeto.Projeto;

import java.util.List;

public record UserCreateDTO (
        String email,
        String senha,
        String username
) {
    public UserCreateDTO {
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de e-mail invalido!");
        }
        if (senha == null || !senha.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")) {
            throw new IllegalArgumentException(
                    "A senha deve ter no minimo 8 caracteres, incluindo uma letra maiuscula, uma minuscula, um numero e um simbolo."
            );
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("O nome de usuario e obrigatorio!");
        }
    }
}
