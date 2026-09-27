package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records.ProjectName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findAllByEventId(Long eventId);

    List<Project> findAllByCategoryAndEventId(
            ProjectCategory category,
            Long eventId
    );
    List<Project> findAllByStatus(ProjectStatus status);

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
