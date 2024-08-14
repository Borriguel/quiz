package dev.borriguel.quiz.exception;

public class UsernameJaEmUsoException extends RuntimeException {
    public UsernameJaEmUsoException() {
        super("Erro: Username já está em uso!");
    }
}
