package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO (

        @NotBlank(message = "O e-mail não pode ficar vazio.")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O nome de usuário não pode ficar vazio.")
        String username
)
{}
