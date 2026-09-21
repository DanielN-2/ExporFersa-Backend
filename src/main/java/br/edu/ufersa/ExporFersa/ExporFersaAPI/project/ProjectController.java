package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    @PostMapping("/events/{eventId}/projects")
    public ResponseEntity<ProjectResponseDTO> create(
            @RequestBody ProjectCreateDTO projeto,
            @PathVariable Long eventId,
            UriComponentsBuilder uriBuilder
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    @GetMapping("/events/{eventId}/projects")
    public ResponseEntity<List<ProjectResponseDTO>> listByEvent(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(null);
    }
    @GetMapping("/projects/mine")
    public ResponseEntity<List<ProjectResponseDTO>> listMine() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO>> listAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> findOne(@PathVariable Long projectId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> update(
            @PathVariable Long projectId,
            @RequestBody ProjectUpdateDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> delete(@PathVariable Long projectId) {
        return ResponseEntity.noContent().build();
    }


}
