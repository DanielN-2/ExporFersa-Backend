package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = "auth")
    List<Comment> findByProjectId(Long projectId, Sort sort);
}