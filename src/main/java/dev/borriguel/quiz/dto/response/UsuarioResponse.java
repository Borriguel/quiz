package dev.borriguel.quiz.dto.response;

import dev.borriguel.quiz.entity.Usuario;

import java.time.LocalDateTime;

public record UsuarioResponse(Long id, String nome, String email, String username, LocalDateTime dataCadastro) {
    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getUsername(), usuario.getDataCadastro());
    }
}
