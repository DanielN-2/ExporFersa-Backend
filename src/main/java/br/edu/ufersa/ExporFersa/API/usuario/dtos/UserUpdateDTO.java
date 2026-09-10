package br.edu.ufersa.ExporFersa.API.usuario.dtos;

public record UserUpdateDTO (
        String email,
        String username
)
{
    public UserUpdateDTO {
        if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }
        if (username != null && username.isBlank()) {
            throw new IllegalArgumentException("O nome de usuário não pode ficar vazio.");
        }
    }
}
