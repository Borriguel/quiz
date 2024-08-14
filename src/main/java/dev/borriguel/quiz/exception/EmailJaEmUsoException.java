package dev.borriguel.quiz.exception;

public class EmailJaEmUsoException extends RuntimeException {
    public EmailJaEmUsoException() {
        super("Erro: Username já está em uso!");
    }
}
