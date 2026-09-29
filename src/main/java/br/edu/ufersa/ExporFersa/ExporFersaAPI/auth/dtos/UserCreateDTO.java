package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.AuthRole;
import jakarta.validation.constraints.*;

public record UserCreateDTO (

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$", message = "A senha deve conter uma letra maiuscula, uma minuscula, um numero e um simbolo.")
        String password,

        @NotBlank(message = "O usuario é obrigatório")
        String username,

        @NotNull(message = "O perfil de usuário é obrigatório")
        AuthRole role
) {}
