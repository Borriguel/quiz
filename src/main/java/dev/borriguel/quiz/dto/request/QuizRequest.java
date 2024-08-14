package dev.borriguel.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;

public record QuizRequest(@NotBlank(message = "Campo titulo obrigatório") String titulo, @NotBlank(message = "Campo descrição obrigatório") String descricao) {
}
