package dev.borriguel.quiz.dto.response;

import dev.borriguel.quiz.entity.Alternativa;

public record AlternativaResponse(Long id, String proposicao) {
    public AlternativaResponse(Alternativa alternativa) {
        this(alternativa.getId(), alternativa.getProposicao());
    }
}
