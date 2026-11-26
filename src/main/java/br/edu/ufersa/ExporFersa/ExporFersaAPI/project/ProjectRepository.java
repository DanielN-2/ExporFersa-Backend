package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    @EntityGraph(attributePaths = "event")
    @Query("SELECT p FROM Project p")
    List<Project> findAllWithEvent();

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndEventId(
            ProjectStatus status,
            Long eventId
    );

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndCategoryAndEventId(
            ProjectStatus status,
            ProjectCategory category,
            Long eventId
    );

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatus(ProjectStatus status);

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndCategory(
            ProjectStatus status,
            ProjectCategory category
    );

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndEventCategory(
            ProjectStatus status,
            EventCategory eventCategory
    );

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndCategoryAndEventCategory(
            ProjectStatus status,
            ProjectCategory category,
            EventCategory eventCategory
    );
    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndEventIdAndEventCategory(
            ProjectStatus status,
            Long eventId,
            EventCategory eventCategory
    );

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByStatusAndCategoryAndEventIdAndEventCategory(
            ProjectStatus status,
            ProjectCategory category,
            Long eventId,
            EventCategory eventCategory
    );

    @EntityGraph(attributePaths = "event")
    List<Project> findAllByAuthId(UUID authId);

//    List<Project> findAllByCategory(ProjectCategory category);

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
}
