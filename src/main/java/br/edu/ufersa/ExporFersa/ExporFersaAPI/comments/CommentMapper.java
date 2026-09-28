package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment toEntity(CommentCreateDTO dto, Long projectId, UUID userId) {
        if(dto == null) {
            return null;
        }
        Comment comment = new Comment(new Project(projectId), new Auth(userId), dto.commentMessage());
        return comment;
    }

    default CommentResponseDTO toResponse(Comment entity) {
        CommentResponseDTO response = new CommentResponseDTO(entity.getId(), entity.getProject().getId(), entity.getAuth().getId(), entity.getCommentMessage());
        return response;
    }

    List<CommentResponseDTO> toResponseList(List<Comment> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "auth", ignore = true)
    void updateEntityFromDto(CommentUpdateDTO dto, @MappingTarget Comment entity);
}
