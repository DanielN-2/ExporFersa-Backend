package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserPasswordUpdateDTO(

        @NotBlank(message = "A senha atual é obrigatória")
        String senhaAntiga,

        @NotBlank(message = "A nova senha é obrigatória")
        @Size(min = 8, message = "A nova senha deve ter no mínimo 6 caracteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$", message = "A senha deve conter uma letra maiuscula, uma minuscula, um numero e um simbolo.")
        String senhaNova
) {}