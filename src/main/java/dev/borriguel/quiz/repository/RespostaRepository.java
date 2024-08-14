package dev.borriguel.quiz.repository;

import dev.borriguel.quiz.entity.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByQuizIdAndUsuarioId(Long quizId, Long usuarioId);

    Optional<Resposta> findByPerguntaIdAndUsuarioId(Long perguntaId, Long usuarioId);
}
