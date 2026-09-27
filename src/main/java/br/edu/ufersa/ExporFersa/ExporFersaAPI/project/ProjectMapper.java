package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.Event;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectPatchDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.Description;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.Summary;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.VideoURL;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
interface ProjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event", source = "eventId")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "status", constant = "PENDING")
    Project toEntity(ProjectCreateDTO dto, Long eventId, UUID userId);

    default Event mapEvent(Long eventId) {
        if (eventId == null) return null;

        return new Event(eventId);
    }
    default User mapUser(UUID userId) {
        if (userId == null) return null;

        return new User(userId);
    }
    default ProjectName mapProjectName(String value) {
        return value == null ? null : new ProjectName(value);
    }

    default VideoURL mapVideoURL(String value) {
        return value == null ? null : new VideoURL(value);
    }

    default Summary mapSummary(String value) {
        return value == null ? null : new Summary(value);
    }

    default Description mapDescription(String value) {
        return value == null ? null : new Description(value);
    }
    ProjectResponseDTO toResponse(Project entity);

    List<ProjectResponseDTO> toResponseList(List<Project> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntityFromDto(ProjectPatchDTO dto, @MappingTarget Project entity);
}

