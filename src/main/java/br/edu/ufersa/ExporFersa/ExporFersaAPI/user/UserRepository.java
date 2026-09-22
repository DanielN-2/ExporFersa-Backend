package br.edu.ufersa.ExporFersa.ExporFersaAPI.user;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
