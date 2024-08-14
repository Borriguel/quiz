package dev.borriguel.quiz.exception;

public class PerguntaRespondidaException extends RuntimeException {
    public PerguntaRespondidaException() {
        super("Erro: Essa pergunta já foi respondida!");
    }
}
