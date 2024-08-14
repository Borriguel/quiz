package dev.borriguel.quiz.dto.request;

import dev.borriguel.quiz.entity.Dificuldade;
import jakarta.validation.constraints.NotBlank;

public record PerguntaRequest(@NotBlank(message = "Campo enunciado obrigatório") String enunciado, Dificuldade dificuldade) {
}
