package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.Comment;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentResponseDTO(
    @NotNull(message = "O identificador do comentário não pode ser nulo na resposta.")
    Long id,

    @NotNull(message = "O comentário precisa estar ligado a um projeto")
    Project project,

    @NotNull(message = "O comentário precisa estar ligado a um usuário")
    User user,

    @NotBlank(message = "O comentário não pode ser vazio")
    @Size(max = 250, min = 1, message = "O comentário precisa ter entre 1 e 250 caracteres")
    String commentMessage
) {
    public static CommentResponseDTO fromEntity(Comment comment) {
        if(comment == null) return null;
        return new CommentResponseDTO(
            comment.getId(),
            comment.getProject(),
            comment.getUser(),
            comment.getCommentMessage()
        );
    }
}