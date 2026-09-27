package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;

public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment validateUpdate(Long id, String message) {
        Comment stored = commentRepository.getReferenceById(id);
        if(stored == null) throw new InvalidOperationException("O comentário não existe.");
        stored.setCommentMessage(message);
        return stored;
    }

    public Comment delete(Long id) {
        Comment stored = commentRepository.getReferenceById(id);
        if(stored == null) throw new InvalidOperationException("O comentário não existe.");
        return stored;
    }
}
