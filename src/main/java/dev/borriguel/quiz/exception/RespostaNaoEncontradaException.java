package dev.borriguel.quiz.exception;

public class RespostaNaoEncontradaException extends RuntimeException {
    public RespostaNaoEncontradaException() {
        super("Erro: Resposta não encontrada!");
    }
}
