package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.Project;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment toEntity(CommentCreateDTO dto, Long projectId, UUID userId) {
        if(dto == null) {
            return null;
        }
        Comment comment = new Comment(new Project(projectId), new User(userId), dto.commentMessage());
        return comment;
    }

    CommentResponseDTO toResponse(Comment entity);

    List<CommentResponseDTO> toResponseList(List<Comment> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CommentUpdateDTO dto, @MappingTarget Comment entity);
}
