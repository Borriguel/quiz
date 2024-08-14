package dev.borriguel.quiz.exception;

public class QuizNaoEncontradoException extends RuntimeException {
    public QuizNaoEncontradoException() {
        super("Erro: Quiz não encontrado!");
    }
}
