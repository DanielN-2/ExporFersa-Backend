package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findAllByStatusAndEventId(
            ProjectStatus status,
            Long eventId
    );

    List<Project> findAllByStatusAndCategoryAndEventId(
            ProjectStatus status,
            ProjectCategory category,
            Long eventId
    );

    List<Project> findAllByStatus(ProjectStatus status);
    List<Project> findAllByStatusAndCategory(
            ProjectStatus status,
            ProjectCategory category
    );

    List<Project> findAllByStatusAndEventCategory(
            ProjectStatus status,
            EventCategory eventCategory
    );

    List<Project> findAllByStatusAndCategoryAndEventCategory(
            ProjectStatus status,
            ProjectCategory category,
            EventCategory eventCategory
    );

    List<Project> findAllByStatusAndEventIdAndEventCategory(
            ProjectStatus status,
            Long eventId,
            EventCategory eventCategory
    );

    List<Project> findAllByStatusAndCategoryAndEventIdAndEventCategory(
            ProjectStatus status,
            ProjectCategory category,
            Long eventId,
            EventCategory eventCategory
    );

    List<Project> findAllByAuthId(UUID authId);

    List<Project> findAllByCategory(ProjectCategory category);

    boolean existsByProjectNameAndEventId(
            ProjectName projectName,
            Long eventId
    );

    boolean existsByProjectNameAndEventIdAndIdNot(
            ProjectName projectName,
            Long eventId,
            Long projectId
    );
}
