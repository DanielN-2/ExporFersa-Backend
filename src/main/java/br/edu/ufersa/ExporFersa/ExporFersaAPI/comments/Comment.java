package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "tb_comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private UUID projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UUID userId;

    @Column(nullable = false, length = 300)
    private String commentMessage;

    protected Comment() {}

    public Comment(UUID projectId, UUID userId, String commentMessage) {
        if(commentMessage == null || commentMessage.isBlank()) {
            throw new IllegalArgumentException("O comentário não pode estar estar vazio!");
        }
        if(projectId == null || userId == null) {
            throw new IllegalArgumentException("O comentário precisa estar associado a um projeto e um usuário!");
        }
    }

    public BigInteger getId() { return id; }
    public UUID getProjectId() { return projectId; }
    public UUID getUserId() { return userId; }
    public String getCommentMessage() { return commentMessage; }
}