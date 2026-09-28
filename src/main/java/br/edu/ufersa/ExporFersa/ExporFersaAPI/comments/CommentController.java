package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import java.net.URI;
import java.util.List;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.comments.dtos.CommentUpdateDTO;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/v1/projects/{projectId}/comments")
@Validated
public class CommentController {

    private final CommentApplicationService CommentApplicationService;

    public CommentController(CommentApplicationService CommentApplicationService) {
        this.CommentApplicationService = CommentApplicationService;
    }

    @PostMapping()
    public ResponseEntity<CommentResponseDTO> createComment(
        @AuthenticationPrincipal Auth authenticatedAuth,
        @PathVariable Long projectId, 
        @RequestBody @Valid CommentCreateDTO dto, UriComponentsBuilder uriBuilder
    ) {
        CommentResponseDTO newComment = CommentApplicationService.create(dto, projectId, authenticatedAuth.getId());
        URI uri = uriBuilder
                    .path("/api/v1/projects/{projectId}/comments/{commentId}")
                    .buildAndExpand(projectId, newComment.id())
                    .toUri();
        return ResponseEntity.created(uri).body(newComment);
    }

    @GetMapping()
    public ResponseEntity<List<CommentResponseDTO>> listComments(@PathVariable Long projectId) {
        List<CommentResponseDTO> comments = CommentApplicationService.list(projectId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(
        @AuthenticationPrincipal Auth authenticatedAuth,
        @PathVariable Long commentId,
        @RequestBody CommentUpdateDTO dto,
        UriComponentsBuilder uriBuilder
    ) {
        CommentResponseDTO updated = CommentApplicationService.update(commentId, dto);
        URI uri = uriBuilder
                    .path("/api/v1/projects/{projectId}/comments/{commentId}")
                    .buildAndExpand(updated.projectId(), commentId)
                    .toUri();
        return ResponseEntity.created(uri).body(updated);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> deleteComment(
        @AuthenticationPrincipal Auth authenticatedAuth,
        @PathVariable Long commentId
    ) {
        CommentApplicationService.delete(commentId);
        return ResponseEntity.noContent().build();
    }
}
