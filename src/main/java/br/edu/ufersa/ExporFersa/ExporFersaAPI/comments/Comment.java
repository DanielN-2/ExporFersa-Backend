package br.edu.ufersa.ExporFersa.ExporFersaAPI.comments;

import java.util.Objects;
import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.Project;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 300)
    private String commentMessage;

    protected Comment() {}

    public Comment(Long projectId, UUID userId, String commentMessage) {
        if(commentMessage == null || commentMessage.isBlank()) {
            throw new IllegalArgumentException("O comentário não pode estar estar vazio!");
        }
        if(projectId == null || userId == null) {
            throw new IllegalArgumentException("O comentário precisa estar associado a um projeto e um usuário!");
        }
    }

    public Comment(Project project, User user, String commentMessage) {
        this.project = project;
        this.user = user;
        this.commentMessage = commentMessage;
    }

    public Comment(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user, commentMessage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (commentMessage == null) {
            if (other.commentMessage != null)
                return false;
        } else if (!commentMessage.equals(other.commentMessage))
            return false;
        return true;
    }

    public Long getId() { return id; }
    public Project getProject() { return project; }
    public User getUser() { return user; }
    public String getCommentMessage() { return commentMessage; }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }
}