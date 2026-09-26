package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectPatchDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectStatusPatchDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/events/{eventId}/projects")
    public ResponseEntity<ProjectResponseDTO> create(
            @RequestBody @Valid ProjectCreateDTO projeto,
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

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> update(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectPatchDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/projects/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid ProjectStatusPatchDTO request
    ) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> delete(@PathVariable Long projectId) {
        return ResponseEntity.noContent().build();
    }


}
