package dev.borriguel.quiz.exception;

import dev.borriguel.quiz.dto.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(QuizNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse quizNaoEncontradoHandle(QuizNaoEncontradoException e, HttpServletRequest http) {
        return new ExceptionResponse(404, "Not Found", e.getMessage(), http.getRequestURI());
    }

    @ExceptionHandler(PerguntaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse perguntaNaoEncontradaHandle(PerguntaNaoEncontradaException e, HttpServletRequest http) {
        return new ExceptionResponse(404, "Not Found", e.getMessage(), http.getRequestURI());
    }

    @ExceptionHandler(AlternativaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse alternativaNaoEncontradaHandle(AlternativaNaoEncontradaException e, HttpServletRequest http) {
        return new ExceptionResponse(404, "Not Found", e.getMessage(), http.getRequestURI());
    }

    @ExceptionHandler(RespostaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse respostaNaoEncontradaHandle(RespostaNaoEncontradaException e, HttpServletRequest http) {
        return new ExceptionResponse(404, "Not Found", e.getMessage(), http.getRequestURI());
    }

    @ExceptionHandler(CargoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse alternativaNaoEncontradaHandle(CargoNaoEncontradoException e, HttpServletRequest http) {
        return new ExceptionResponse(404, "Not Found", e.getMessage(), http.getRequestURI());
    }

    @ExceptionHandler(AutorizacaoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse autorizacaoHandle(AutorizacaoException e, HttpServletRequest http) {
        return new ExceptionResponse(403, "Forbidden", e.getMessage(), http.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse validacaoHandle(MethodArgumentNotValidException e, HttpServletRequest http) {
        StringBuilder erros = new StringBuilder();
        for (var error : e.getBindingResult().getFieldErrors()) {
            erros.append(error.getDefaultMessage()).append(" ");
        }
        return new ExceptionResponse(400, "Bad Request", erros.toString(), http.getRequestURI());
    }

    @ExceptionHandler(PerguntaRespondidaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse perguntaRespondidaHandle(PerguntaRespondidaException e, HttpServletRequest http) {
        return new ExceptionResponse(409, "Conflict", e.getMessage(), http.getRequestURI());
    }
}
