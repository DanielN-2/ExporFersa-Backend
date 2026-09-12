package br.edu.ufersa.ExporFersa.ExporFersaAPI.api.comentario;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.comentario.dtos.CommentCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.comentario.dtos.CommentResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.comentario.dtos.CommentUpdateDTO;

@RestController 
@RequestMapping("/api/v1/projects/{projectId}/comments")
public class ComentarioController {
    
    @PostMapping()
    public ResponseEntity<CommentResponseDTO> criarComentario(@RequestBody CommentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping()
    public ResponseEntity<List<CommentResponseDTO>> listarComentarios(@PathVariable UUID id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> atualizarComentario(@PathVariable UUID id, @RequestBody CommentUpdateDTO dto) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> excluirComentario(@PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }
}
