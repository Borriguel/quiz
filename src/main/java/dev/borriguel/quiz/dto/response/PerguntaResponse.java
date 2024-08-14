package dev.borriguel.quiz.dto.response;

import dev.borriguel.quiz.entity.Dificuldade;
import dev.borriguel.quiz.entity.Pergunta;

import java.util.List;

public record PerguntaResponse(Long id, String enunciado, Dificuldade dificuldade, List<AlternativaResponse> alternativas) {
    public PerguntaResponse(Pergunta pergunta) {
        this(pergunta.getId(), pergunta.getEnunciado(), pergunta.getDificuldade(), pergunta.getAlternativas().stream().map(AlternativaResponse::new).toList());
    }
}
