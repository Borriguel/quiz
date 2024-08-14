package dev.borriguel.quiz.repository;

import dev.borriguel.quiz.entity.Cargo;
import dev.borriguel.quiz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByNome(Role nome);
}
