package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProjectPatchDTO(
        String nomeProjeto,

        List<@NotBlank(
                message = "O nome dos autores não pode ser vazio!"
        ) String> autores,

        @Pattern(
                regexp = "^https?://.+",
                message = "A URL do vídeo deve ser um link válido!"
        )
        String videoURL,

        @Size(
                max = 1000,
                message = "O resumo não pode possuir mais de 1000 caracteres!"
        )
        String resumo,

        @Size(
                max = 5000,
                message = "A descrição não pode possuir mais de 5000 caracteres!"
        )
        String descricao
) {
}
