package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Auth, UUID> {

    Optional<Auth> findByEmail(String email);
    boolean existsByEmail(String email);

}
