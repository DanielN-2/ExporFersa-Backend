package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
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
import java.util.UUID;

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
            @AuthenticationPrincipal Auth user,
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
            @RequestParam(required = false) ProjectCategoryDTO category,
            @AuthenticationPrincipal Auth user
    ) {
        return ResponseEntity.ok(service.getProjectsByEvent(category, eventId, user));
    }



    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO>> listAll(
            @RequestParam(required = false) ProjectCategoryDTO category,
            @AuthenticationPrincipal Auth user
    ) {
        return ResponseEntity.ok(service.getProjects(category, user));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/projects/mine")
    public ResponseEntity<List<ProjectResponseDTO>> listMyProjects(
            @AuthenticationPrincipal Auth user
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
    @GetMapping("admin/projects/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<ProjectStatsDTO> getProjectStats() {
        return ResponseEntity.ok(service.getProjectStats());
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> findOne(
            @PathVariable Long projectId,
            @AuthenticationPrincipal Auth user
    ) {
        return ResponseEntity.ok(service.getById(projectId, user));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/projects/{projectId}/image")
    public ResponseEntity<ProjectResponseDTO> addImage(
        @PathVariable Long projectId,
        @ModelAttribute @Valid ProjectImageCreateDTO dto,
        @AuthenticationPrincipal Auth user
    ) {
        return ResponseEntity.ok(service.addImage(projectId, dto, user));
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDTO> update(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectPatchDTO dto,
            @AuthenticationPrincipal Auth user
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
    @PatchMapping("/projects/{projectId}/image/{imageId}")
    public ResponseEntity<ProjectResponseDTO> updateImage(
        @PathVariable Long projectId,
        @PathVariable UUID imageId,
        @AuthenticationPrincipal Auth user,
        @RequestBody ProjectImageUpdateDTO dto
    ) {
        return ResponseEntity.ok(service.updateImage(projectId, imageId, user, dto));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long projectId,
            @AuthenticationPrincipal Auth user
    ) {
        service.delete(projectId, user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/projects/{projectId}/image/{imageId}")
    public ResponseEntity<ProjectResponseDTO> deleteImage(
        @PathVariable Long projectId,
        @PathVariable UUID imageId,
        @AuthenticationPrincipal Auth user
    ) {
        return ResponseEntity.ok(service.deleteImage(projectId, imageId, user));
    }
}
