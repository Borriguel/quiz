package dev.borriguel.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(@NotBlank(message = "Campo username obrigatório.") String username,
                           @NotBlank(message = "Campo senha obrigatório.") @Length(max = 32, message = "Tamanho máximo 32 caracteres") String senha) {
}
