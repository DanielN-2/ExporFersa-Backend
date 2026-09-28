package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.AuthRepository;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.Project;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.ProjectRepository;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AuthRepository authRepository;
    private final ProjectRepository projectRepository;

    public CommentService(CommentRepository commentRepository, AuthRepository authRepository, ProjectRepository projectRepository) {
        this.commentRepository = commentRepository;
        this.authRepository = authRepository;
        this.projectRepository = projectRepository;
    }

    public Comment create(Comment comment) {
        Optional<Auth> userExists = authRepository.findById(comment.getAuth().getId());
        if(userExists.isEmpty()) throw new InvalidOperationException("O usuário não existe.");
        Optional<Project> projectExists = projectRepository.findById(comment.getProject().getId());
        if(projectExists.isEmpty()) throw new InvalidOperationException("O projeto não existe.");
        return new Comment(projectExists.get(), userExists.get(), comment.getCommentMessage());
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
