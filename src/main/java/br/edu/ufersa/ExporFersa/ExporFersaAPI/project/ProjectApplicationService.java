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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class ProjectApplicationService {
    private final ProjectRepository repository;
    private final ProjectService service;
    private final EventInternalApi eventApi;
    private final ProjectMapper mapper;

    ProjectApplicationService(
            ProjectRepository repository,
            ProjectService service,
            EventInternalApi eventApi,
            ProjectMapper mapper
    ) {
        this.repository = repository;
        this.service = service;
        this.eventApi = eventApi;
        this.mapper = mapper;

    }
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getProjects(ProjectCategoryDTO category, Auth user) {
        List<Project> projects;
        System.out.println("Usuário: " + user);
        System.out.println("Role: " + (user != null ? user.getRole() : null));
        System.out.println("Categoria: " + category);
        if (user != null && (user.getRole() == AuthRole.USER || user.getRole() == AuthRole.ADMIN )) {
            projects = category == null
                    ? repository.findAllByStatus(ProjectStatus.APPROVED)
                    : repository.findAllByStatusAndCategory(ProjectStatus.APPROVED,category.toDomain());
        } else {
            projects = category == null
                    ? repository.findAllByStatusAndEventCategory(ProjectStatus.APPROVED, EventCategory.EXTENSAO)
                    : repository.findAllByStatusAndCategoryAndEventCategory(ProjectStatus.APPROVED, category.toDomain(), EventCategory.EXTENSAO);
        }
        return mapper.toResponseList(projects);
    }
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getProjectsByEvent(ProjectCategoryDTO category, Long eventId, Auth user) {
        List<Project> projects;
        if (user != null && (user.getRole() == AuthRole.USER || user.getRole() == AuthRole.ADMIN )) {
            projects= category == null
                    ? repository.findAllByStatusAndEventId(ProjectStatus.APPROVED, eventId)
                    : repository.findAllByStatusAndCategoryAndEventId(ProjectStatus.APPROVED, category.toDomain(), eventId);

        } else {
            projects = category == null
                    ? repository.findAllByStatusAndEventIdAndEventCategory(ProjectStatus.APPROVED, eventId, EventCategory.EXTENSAO)
                    : repository.findAllByStatusAndCategoryAndEventIdAndEventCategory(
                            ProjectStatus.APPROVED,
                            category.toDomain(),
                            eventId,
                            EventCategory.EXTENSAO
                    );
        }
        return mapper.toResponseList(projects);
    }
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getAdminProjects(StatusDTO status) {
        List<Project> projects = status == null
                ? repository.findAllByStatus(ProjectStatus.PENDING)
                : repository.findAllByStatus(status.toDomain());
        return mapper.toResponseList(projects);
    }
    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getMyProjects(Auth user) {
        return mapper.toResponseList(
                repository.findAllByAuthId(user.getId())
        );
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO getById(long projectId, Auth user) {
        Project project = repository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado!"));

        if(project.getStatus() != ProjectStatus.APPROVED && user.getRole() != AuthRole.ADMIN) {
            throw new AccessDeniedException("Você não possui permissão para visualizar esse projeto.");
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
}
