package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByEventoId(Long eventoId);

    List<Project> findAllByStatus(ProjectStatus status);

    List<Project> findAllByUserId(Long userId);

    List<Project> findAllByCategory(ProjectCategory category);

    boolean existsByNomeProjetoAndEventoId(
            String nomeProjeto,
            Long eventoId
    );
}
