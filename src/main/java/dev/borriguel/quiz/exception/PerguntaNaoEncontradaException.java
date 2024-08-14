package dev.borriguel.quiz.exception;

public class PerguntaNaoEncontradaException extends RuntimeException {
    public PerguntaNaoEncontradaException() {
        super("Erro: Pergunta não encontrada!");
    }
}
