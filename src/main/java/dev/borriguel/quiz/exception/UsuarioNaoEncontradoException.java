package dev.borriguel.quiz.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException() {
        super("Erro: Usuário não encontrado!");
    }
}
