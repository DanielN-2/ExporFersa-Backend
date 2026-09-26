package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentCreateDTO(
    @NotBlank(message = "O comentário não pode ser vazio")
    @Size(max = 250, min = 1, message = "O comentário precisa ter entre 1 e 250 caracteres")
    String commentMessage
) {
    public CommentCreateDTO {}
}
