package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.Project;

@Entity
@Table(name = "auth")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Setter
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private AuthRole role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> likes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projectsImIn = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == AuthRole.ADMIN) {
			return List.of(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("ROLE_GUEST")
			);
        }
		if(this.role == AuthRole.USER) {
			return List.of(
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("ROLE_GUEST")
			);
        }
		return List.of(new SimpleGrantedAuthority("ROLE_GUEST"));
    }
    
    protected Auth(String username, String email, String password, AuthRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Auth(UUID id) {
        this.id = id;
    }
}
