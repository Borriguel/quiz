package dev.borriguel.quiz.repository;

import dev.borriguel.quiz.entity.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    @Query("select p from Pergunta p where p.quiz.id = ?1")
    List<Pergunta> findByQuizId(Long id);

}
