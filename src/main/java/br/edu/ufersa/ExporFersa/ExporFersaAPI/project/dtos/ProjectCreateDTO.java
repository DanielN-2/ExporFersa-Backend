package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProjectCreateDTO(
        @NotBlank(message = "O nome do projeto é obrigatório!")
        String nomeProjeto,

        @NotEmpty(message = "O projeto deve possuir pelo menos um autor!")
        @Size(min = 1, message = "O projeto deve possuir pelo menos um autor!")
        List<@NotBlank(message = "O nome dos autores não pode ser vazio!") String> autores,

        @NotBlank(message = "A URL do vídeo é obrigatória!")
        @Pattern(
                regexp = "^https?://.+",
                message = "A URL do vídeo deve ser um link válido!"
        )
        String videoURL,

        @NotBlank(message = "O resumo é obrigatório!")
        @Size(max = 1000, message = "O resumo não pode possuir mais de 1000 caracteres!")
        String resumo,

        @NotBlank(message = "A descrição é obrigatória!")
        @Size(max = 5000, message = "A descrição não pode possuir mais de 5000 caracteres!")
        String descricao
) {
}


