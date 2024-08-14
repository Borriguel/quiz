package dev.borriguel.quiz.exception;

public class AlternativaNaoEncontradaException extends RuntimeException {
    public AlternativaNaoEncontradaException() {
        super("Erro: Alternativa não encontrada!");
    }
}
