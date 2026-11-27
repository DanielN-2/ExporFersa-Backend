package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.AuthRole;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.Event;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventInternalApi;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.*;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.AccessDeniedException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.EntityNotFoundException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.ImageData;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.services.FileService;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
class ProjectApplicationService {
    private final ProjectRepository repository;
    private final ProjectService service;
    private final EventInternalApi eventApi;
    private final ProjectMapper mapper;
    private final FileService fileService;

    ProjectApplicationService(
            ProjectRepository repository,
            ProjectService service,
            EventInternalApi eventApi,
            ProjectMapper mapper,
            FileService fileService
    ) {
        this.repository = repository;
        this.service = service;
        this.eventApi = eventApi;
        this.mapper = mapper;
        this.fileService = fileService;

    }
    private List<Project> findProjects(
            ProjectCategory category,
            Long eventId,
            EventCategory eventCategory
    ) {
        return repository.findAll(
                ProjectSpecification.withFilters(
                        ProjectStatus.APPROVED,
                        category,
                        eventId,
                        eventCategory
                ),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getProjects(ProjectCategoryDTO category, Auth user) {
        boolean hasFullAccess = user != null &&
                (user.getRole() == AuthRole.USER ||
                        user.getRole() == AuthRole.ADMIN);

        List<Project> projects = findProjects(
                category == null ? null : category.toDomain(),
                null,
                hasFullAccess ? null : EventCategory.EXTENSAO
        );

        return mapper.toResponseList(projects);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getProjectsByEvent(ProjectCategoryDTO category, Long eventId, Auth user) {
        boolean hasFullAccess = user != null &&
                (user.getRole() == AuthRole.USER ||
                        user.getRole() == AuthRole.ADMIN);

        List<Project> projects = findProjects(
                category == null ? null : category.toDomain(),
                eventId,
                hasFullAccess ? null : EventCategory.EXTENSAO
        );

        return mapper.toResponseList(projects);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getAdminProjects(StatusDTO status) {
        List<Project> projects = repository.findAll(
                ProjectSpecification.withFilters(
                        status == null ? null : status.toDomain(),
                        null,
                        null,
                        null
                ),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        return mapper.toResponseList(projects);
    }
    public ProjectStatsDTO getProjectStats() {
        return new ProjectStatsDTO(
                repository.countByStatus(ProjectStatus.APPROVED),
                repository.countByStatus(ProjectStatus.PENDING),
                repository.countByStatus(ProjectStatus.REJECTED)
        );
    }
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getMyProjects(Auth user) {
        return mapper.toResponseList(
                repository.findAllByAuthId(
                        user.getId(),
                        Sort.by(Sort.Direction.DESC, "createdAt")
                )
        );
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO getById(long projectId, Auth user) {
        Project project = repository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado!"));

        if (project.getStatus() != ProjectStatus.APPROVED
                && (user == null || user.getRole() != AuthRole.ADMIN)) {
            throw new AccessDeniedException(
                    "Você não possui permissão para visualizar esse projeto."
            );
        }

        Event projectEvent = project.getEvent();
        boolean hasFullAccess = user != null
                && (user.getRole() == AuthRole.ADMIN
                || user.getRole() == AuthRole.USER);
        if(projectEvent.getCategory() != EventCategory.EXTENSAO && !hasFullAccess) {
            throw new AccessDeniedException("Somente membros da instituição podem visualizar esse projeto.");
        }


        return mapper.toResponse(project);
    }

    @Transactional
    public ProjectResponseDTO create(
            ProjectCreateDTO dto,
            Long eventId,
            Auth  user
    ) {
        Project newProject = mapper.toEntity(dto, eventId, user.getId());
        eventApi.findById(newProject.getEvent().getId());
        service.validateProjectName(
                newProject.getProjectName(),
                newProject.getEvent().getId()
        );
        Project savedProject = repository.save(newProject);
        return mapper.toResponse(savedProject);
    }

    @Transactional
    public ProjectResponseDTO update(
            Long projectId,
            ProjectPatchDTO dto,
            Auth user
    ) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Projeto não encontrado."
                ));

        if (!project.getAuth().getId().equals(user.getId())) {
            throw new AccessDeniedException(
                    "Você não possui permissão para alterar este projeto."
            );
        }

        project.ensureCanUpdate();

        if (dto.projectName() != null) {
            service.validateProjectNameUpdate(
                    new ProjectName(dto.projectName()),
                    project.getEvent().getId(),
                    project.getId()
            );
        }

        mapper.updateEntityFromDto(dto, project);

        return mapper.toResponse(project);
    }
    @Transactional
    public void updateStatus(
            Long projectId,
            StatusDTO status
    ) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Projeto não encontrado."
                ));

        switch (status) {
            case APPROVED -> project.approve();
            case REJECTED -> project.reject();
            case PENDING -> throw new InvalidOperationException(
                    "Não é possível alterar o status de um projeto para PENDING."
            );
        }
    }
    @Transactional
    public void delete(Long projectId, Auth user) {
        Project project = repository.findById(projectId)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Projeto não encontrado."
            ));

        if (!project.getAuth().getId().equals(user.getId())) {
            throw new AccessDeniedException(
                "Você não possui permissão para excluir este projeto."
            );
        }

        project.ensureCanDelete();

        repository.delete(project);
    }

    @Transactional
    public ProjectResponseDTO addImage(Long projectId, ProjectImageCreateDTO dto, Auth user) {
        Project project = repository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(
            "Projeto não encontrado."
        ));

        if (!project.getAuth().getId().equals(user.getId())) {
            throw new AccessDeniedException("Você não tem permissão para adicionar imagens nesse projeto.");
        }

        ImageData newImage = fileService.saveImage("projetos", dto.imageTitle(), dto.image());
        project.addImages(newImage);

        repository.save(project);
        return mapper.toResponse(project);
    }

    @Transactional 
    public ProjectResponseDTO updateImage(Long projectId, UUID imageId, Auth user, ProjectImageUpdateDTO dto) {
        Project project = repository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(
            "Projeto não encontrado."
        ));

        if (!project.getAuth().getId().equals(user.getId())) {
            throw new AccessDeniedException("Você não tem permissão para alterar imagens nesse projeto.");
        }

        int imageIndex = service.validateProjectImage(project, imageId);

        mapper.updateProjectImageFromDTO(dto, project.getImages().get(imageIndex));
        repository.save(project);

        return mapper.toResponse(project);
    }

    @Transactional
    public ProjectResponseDTO deleteImage(Long projectId, UUID imageId, Auth user) {
        Project project = repository.findById(projectId).orElseThrow(() -> new EntityNotFoundException(
            "Projeto não encontrado."
        ));

        if (!project.getAuth().getId().equals(user.getId())) {
            throw new AccessDeniedException("Você não tem permissão para alterar imagens nesse projeto.");
        }

        int imageIndex = service.validateProjectImage(project, imageId);
        String imageURL = project.getImages().get(imageIndex).imageURL();

        if (fileService.existsImage(imageURL)) {
            fileService.deleteImage(imageURL);
        }

        project.getImages().remove(imageIndex);
        repository.save(project);

        return mapper.toResponse(project);
    }
}
