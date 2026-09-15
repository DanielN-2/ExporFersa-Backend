package br.edu.ufersa.ExporFersa.ExporFersaAPI.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.entity.Project;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(
            name = "usuario_likes",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Project> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_projetos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Project> projetosQueFacoParte = new ArrayList<>();
}
