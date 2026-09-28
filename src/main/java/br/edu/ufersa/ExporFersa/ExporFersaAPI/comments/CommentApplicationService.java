package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
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
        Comment newComment = commentService.create(commentMapper.toEntity(dto, projectId, userId));
        Comment saved = commentRepository.save(newComment);
        CommentResponseDTO mapped = commentMapper.toResponse(saved);
        return mapped;
    }

    public List<CommentResponseDTO> list(Long projectId) {
        List<Comment> list = commentRepository.findByProjectId(projectId);
        return commentMapper.toResponseList(list);
    }

    public CommentResponseDTO update(Long commentId, CommentUpdateDTO dto, UUID userId) {
        Comment updated = commentService.validateUpdate(commentId, dto.commentMessage(), userId);
        return commentMapper.toResponse(commentRepository.save(updated));
    }

    public void delete(Long commentId, UUID userId) {
        Comment toDelete = commentService.delete(commentId, userId);
        commentRepository.delete(toDelete);
    }
}
