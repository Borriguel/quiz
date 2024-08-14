package dev.borriguel.quiz.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UsuarioRequest(@NotBlank(message = "Campo nome obrigatório.") String nome, @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Insira um username válido sem espaços em branco com letras, números e - ou _.") String username, @Email(message = "Insira um email válido.") String email, @NotBlank(message = "Campo senha obrigatório.") @Length(max = 32, message = "Tamanho máximo 32 caracteres") String senha, LocalDate dataNascimento) {
}
