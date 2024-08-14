package dev.borriguel.quiz.exception;

public class AutorizacaoException extends RuntimeException {
    public AutorizacaoException() {
        super("Erro: Usuário não possui autorização para realizar essa operação.");
    }
}
