package dev.borriguel.quiz.dto.response;

import dev.borriguel.quiz.entity.Dificuldade;
import dev.borriguel.quiz.entity.Resposta;

public record ResultadoPerguntaResponse(Long id, String enunciado, Dificuldade dificuldade,
                                        AlternativaResponse alternativaEscolhida) {
    public ResultadoPerguntaResponse(Resposta resposta) {
        this(resposta.getPergunta().getId(), resposta.getPergunta().getEnunciado(), resposta.getPergunta().getDificuldade(), new AlternativaResponse(resposta.getAlternativaEscolhida()));
    }
}
