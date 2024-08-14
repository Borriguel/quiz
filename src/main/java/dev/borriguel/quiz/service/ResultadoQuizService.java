package dev.borriguel.quiz.service;

import dev.borriguel.quiz.dto.response.ResultadoQuizResponse;

public interface ResultadoQuizService {
    ResultadoQuizResponse calcularResultado(Long quizId, Long usuarioId);
}
