package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.DataConflictException;
import org.springframework.stereotype.Service;

@Service
class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public void validateProjectName(ProjectName name, Long eventId) {
        boolean exists = projectRepository.existsByProjectNameAndEventId(
                name,
                eventId

        );

        if (exists) {
            throw new DataConflictException(
                    "Já existe um projeto com este nome neste evento."
            );
        }
    }

    public void validateProjectNameUpdate(ProjectName name, Long eventId, long projectId) {
        boolean exists = projectRepository.existsByProjectNameAndEventIdAndIdNot(
                name,
                eventId,
                projectId

        );

        if (exists) {
            throw new DataConflictException(
                    "Já existe um projeto com este nome neste evento."
            );
        }
    }
}
