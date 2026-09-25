package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.User;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.Event;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "project_name"))
    private ProjectName projectName;

    @ElementCollection
    @CollectionTable(name = "project_authors", joinColumns = @JoinColumn(name = "project_id"))
    private List<Author> authors;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "video_url"))
    private VideoURL videoURL;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "summary", length = 1000))
    private Summary summary;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "description", length = 5000))
    private Description description;

    @ElementCollection
    @CollectionTable(name = "project_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image_path")
    private List<String> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectCategory category;

    protected Project() {
    }

    // refatoração futura - implementar padrão Builder
    Project(
            ProjectName projectName,
            List<Author> authors,
            VideoURL videoURL,
            Summary summary,
            Description description,
            ProjectCategory category,
            Event event,
            User user
    ) {
        validateInvariants(
                projectName,
                authors,
                videoURL,
                summary,
                description,
                category,
                event,
                user
        );
        this.projectName = projectName;
        this.authors = new ArrayList<>(authors);
        this.videoURL = videoURL;
        this.summary = summary;
        this.description = description;
        this.category = category;
        this.event = event;
        this.user = user;
        this.status = ProjectStatus.PENDING;
    }

    private void validateInvariants(
            ProjectName projectName,
            List<Author> authors,
            VideoURL videoURL,
            Summary summary,
            Description description,
            ProjectCategory category,
            Event event,
            User user
    ) {
        if (projectName == null) throw new IllegalArgumentException("O nome do projeto é obrigatório.");
        if (authors == null || authors.isEmpty())
            throw new IllegalArgumentException("O projeto deve possuir pelo menos um autor.");
        if (videoURL == null) throw new IllegalArgumentException("A URL do vídeo é obrigatória.");
        if (summary == null) throw new IllegalArgumentException("O resumo é obrigatório.");
        if (description == null) throw new IllegalArgumentException("A descrição é obrigatória.");
        if (category == null) throw new IllegalArgumentException("A categoria é obrigatória.");
        if (event == null) throw new IllegalArgumentException("O evento é obrigatório.");
        if (user == null) throw new IllegalArgumentException("O usuário é obrigatório.");
    }

    void approve() {
        validateTransition(ProjectStatus.APPROVED);
        this.status = ProjectStatus.APPROVED;
    }

    void reject() {
        validateTransition(ProjectStatus.REJECTED);
        this.status = ProjectStatus.REJECTED;
    }

    void validateTransition(ProjectStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new IllegalStateException(
                    String.format("Transição inválida: projeto está em '%s' e não pode mover para '%s'",
                            this.status, nextStatus)
            );
        }
    }

    void addImages(List<String> newImages) {
        this.images.addAll(newImages);
    }
    void updateInfo(
            ProjectName projectName,
            List<Author> authors,
            VideoURL videoURL,
            Summary summary,
            Description description,
            ProjectCategory category
    ) {
        if (status != ProjectStatus.PENDING) {
            throw new IllegalStateException(
                    "Apenas projetos pendentes podem ser atualizados!"
            );
        }
        if (projectName != null) this.projectName = projectName;
        if (authors != null) this.authors = new ArrayList<>(authors);
        if (videoURL != null) this.videoURL = videoURL;
        if (summary != null) this.summary = summary;
        if (description != null) this.description = description;
        if (category != null) this.category = category;
    }
    Long getId() {return id;}
    List<String> getImages() {return images;}
    Event getEvent() {return event;}
    User getUser() {return user;}
    ProjectCategory getCategory() {return category;}
    ProjectName getProjectName() {return projectName;}
    List<Author> getAuthors() {return List.copyOf(authors);}
    VideoURL getVideoURL() {return videoURL;}
    Summary getSummary() {return summary;}
    Description getDescription() {return description;}
}
