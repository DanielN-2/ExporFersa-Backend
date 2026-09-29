package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

import jakarta.validation.constraints.*;

import java.util.List;

public record ProjectResponseDTO(
        @NotNull(message = "O ID é obrigatório!")
        Long id,

        @NotBlank(message = "O nome do projeto é obrigatório!")
        String projectName,

        @NotEmpty(message = "O projeto deve possuir pelo menos um autor!")
        List<@NotBlank(message = "O nome dos autores não pode ser vazio!") String> authors,

        @NotBlank(message = "A URL do vídeo é obrigatória!")
        @Pattern(
                regexp = "^https?://.+",
                message = "A URL do vídeo deve ser um link válido!"
        )
        String videoURL,

        @NotBlank(message = "O resumo é obrigatório!")
        @Size(max = 1000, message = "O resumo não pode possuir mais de 1000 caracteres!")
        String summary,

        @NotBlank(message = "A descrição é obrigatória!")
        @Size(max = 5000, message = "A descrição não pode possuir mais de 5000 caracteres!")
        String description,

        @NotNull(message = "A categoria é obrigatória!")
        ProjectCategoryDTO category
) {
}

