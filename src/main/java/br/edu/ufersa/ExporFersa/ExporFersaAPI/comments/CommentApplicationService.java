package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import java.util.List;
import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentUpdateDTO;
import jakarta.transaction.Transactional;

public class CommentApplicationService {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentApplicationService(
        CommentService commentService,
        CommentRepository commentRepository,
        CommentMapper commentMapper
    ) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional 
    public CommentResponseDTO create(CommentCreateDTO dto, Long projectId, UUID userId) {
        Comment newComment = commentMapper.toEntity(dto, projectId, userId);
        return commentMapper.toResponse(commentRepository.save(newComment));
    }

    public List<CommentResponseDTO> list(Long projectId) {
        List<Comment> list = commentRepository.findByProjectId(projectId);
        return commentMapper.toResponseList(list);
    }

    public CommentResponseDTO update(Long commentId, CommentUpdateDTO dto) {
        Comment updated = commentService.validateUpdate(commentId, dto.commentMessage());
        return commentMapper.toResponse(commentRepository.save(updated));
    }

    public void delete(Long commentId) {
        Comment toDelete = commentService.delete(commentId);
        commentRepository.delete(toDelete);
    }
}
