package dev.borriguel.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AlternativaRequest(@NotBlank(message = "Campo proposição obrigatório") String proposicao, Boolean correta) {
}
