package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.DataConflictException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.ImageNotFoundException;

import java.util.UUID;

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

    public int validateProjectImage(Project project, UUID imageId) {
        int index = 0;

        for (var image : project.getImages()) {
            if (image.id().equals(imageId)) {
                return index;
            }

            index++;
        }

        throw new ImageNotFoundException("Imagem não encontrada.");
    }
}
