package dev.borriguel.quiz.dto.response;

import dev.borriguel.quiz.entity.Quiz;

import java.time.LocalDate;

public record QuizResponse(Long id, String titulo, String descricao, LocalDate dataCriacao, String criador) {
    public QuizResponse(Quiz quiz) {
        this(quiz.getId(), quiz.getTitulo(), quiz.getDescricao(), quiz.getDataCriacao(), quiz.getCriador().getUsername());
    }
}
