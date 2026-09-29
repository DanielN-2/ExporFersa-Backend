package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.Event;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectCategoryDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectPatchDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos.ProjectResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.*;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
interface ProjectMapper {

    // DTO → Entity

    default Project toEntity(
            ProjectCreateDTO dto,
            Long eventId,
            UUID userId
    ) {
        if (dto == null) {
            return null;
        }

        return new Project(
                mapProjectName(dto.projectName()),
                mapAuthors(dto.authors()),
                mapVideoURL(dto.videoURL()),
                mapSummary(dto.summary()),
                mapDescription(dto.description()),
                mapProjectCategory(dto.category()),
                new Event(eventId),
                new Auth(userId)
        );
    }

    default ProjectName mapProjectName(String value) {
        return value == null ? null : new ProjectName(value);
    }

    default List<Author> mapAuthors(List<String> authors) {
        if (authors == null) {
            return null;
        }

        return authors.stream()
                .map(Author::new)
                .toList();
    }

    default ProjectCategory mapProjectCategory(ProjectCategoryDTO category) {
        return category == null
                ? null
                : ProjectCategory.valueOf(category.name());
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


    // Entity → DTO

    ProjectResponseDTO toResponse(Project entity);

    List<ProjectResponseDTO> toResponseList(List<Project> entities);

    default String mapProjectName(ProjectName value) {
        return value == null ? null : value.value();
    }

    default List<String> mapAuthorsToStrings(List<Author> authors) {
        if (authors == null) {
            return null;
        }

        return authors.stream()
                .map(Author::name)
                .toList();
    }

    default String mapVideoURL(VideoURL value) {
        return value == null ? null : value.value();
    }

    default String mapSummary(Summary value) {
        return value == null ? null : value.value();
    }

    default String mapDescription(Description value) {
        return value == null ? null : value.value();
    }


    // PATCH

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "auth", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntityFromDto(
            ProjectPatchDTO dto,
            @MappingTarget Project entity
    );
}