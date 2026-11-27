package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;
@NullMarked
public interface ProjectRepository extends
        JpaRepository<Project, Long>,
        JpaSpecificationExecutor<Project> {

    @Override
    @EntityGraph(attributePaths = "event")
    List<Project> findAll(
            @Nullable Specification<Project> spec,
            Sort sort
    );

    boolean existsByProjectNameAndEventId(
            ProjectName projectName,
            Long eventId
    );

    boolean existsByProjectNameAndEventIdAndIdNot(
            ProjectName projectName,
            Long eventId,
            Long projectId
    );

    long countByStatus(ProjectStatus status);

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByAuthId(UUID authId, Sort sort);
}