package dev.borriguel.quiz.service;

import dev.borriguel.quiz.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Quiz salvar(Quiz quiz);
    Page<Quiz> buscarTodos(Pageable pageable);
    Quiz buscarPorId(Long id);
    Quiz atualizar(Long id, Quiz quiz);
    void excluir(Long id);
    void validarUsuarioCriadorDoQuiz(Quiz quiz);
}
