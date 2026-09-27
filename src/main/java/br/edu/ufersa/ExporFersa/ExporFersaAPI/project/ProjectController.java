package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    private final ProjectApplicationService service;

    ProjectController(ProjectApplicationService service) {
        this.service = service;
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/events/{eventId}/projects")
    public ResponseEntity<ProjectResponseDTO> create(
            @RequestBody @Valid ProjectCreateDTO projeto,
            @PathVariable Long eventId,
            @AuthenticationPrincipal User user,
            UriComponentsBuilder uriBuilder
    ) {
        ProjectResponseDTO createdProject = service.create(projeto, eventId, user);
        URI uri = uriBuilder
                .path("/api/v1/projects/{projectId}")
                .buildAndExpand(createdProject.id())
                .toUri();
        return ResponseEntity.created(uri).body(createdProject);
    }

    @GetMapping("/events/{eventId}/projects")
    public ResponseEntity<List<ProjectResponseDTO>> listByEvent(
            @PathVariable Long eventId,
            @RequestParam(required = false) ProjectCategoryDTO category
    ) {
        return ResponseEntity.ok(service.getProjectsByEvent(category, eventId));
    }



    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO>> listAll(
            @RequestParam(required = false) ProjectCategoryDTO category
    ) {
        return ResponseEntity.ok(service.getProjects(category));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/projects/mine")
    public ResponseEntity<List<ProjectResponseDTO>> listMyProjects(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(service.getMyProjects(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/projects")
    public ResponseEntity<List<ProjectResponseDTO>> listAdminProjects(
            @RequestParam(required = false) StatusDTO status
    ) {
        return ResponseEntity.ok(
                service.getAdminProjects(status)
        );
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> findOne(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.getById(projectId));
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> update(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectPatchDTO dto,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(service.update(projectId, dto, user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/projects/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid ProjectStatusPatchDTO request
    ) {
        service.updateStatus(id, request.status());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long projectId,
            @AuthenticationPrincipal User user
    ) {
        service.delete(projectId, user);
        return ResponseEntity.noContent().build();
    }


}
