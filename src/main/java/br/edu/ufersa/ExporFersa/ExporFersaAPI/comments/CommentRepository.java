package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, BigInteger> {
    List<Comment> findByProjectId(UUID projectId);
}