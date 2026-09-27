package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.AuthRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginDTO (

        @NotBlank(message = "O usuario é obrigatório")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotNull(message = "O perfil de usuário é obrigatório")
        AuthRole role
) {}