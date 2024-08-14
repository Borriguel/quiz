package dev.borriguel.quiz.exception;

public class CargoNaoEncontradoException extends RuntimeException {
    public CargoNaoEncontradoException() {
        super("Erro: Cargo não encontrado!");
    }
}
