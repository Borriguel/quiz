package dev.borriguel.quiz.dto.response;

public record ExceptionResponse(int status, String error, String message, String path) {
}
