package dev.borriguel.quiz.dto.response;

import dev.borriguel.quiz.entity.Resposta;

import java.time.LocalDateTime;

public record RespostaResponse(Long id, QuizResponse quiz, PerguntaResponse pergunta, AlternativaResponse alternativa,
                               UsuarioResponse usuario, LocalDateTime dataResposta) {
    public RespostaResponse(Resposta resposta) {
        this(resposta.getId(), new QuizResponse(resposta.getQuiz()), new PerguntaResponse(resposta.getPergunta()), new AlternativaResponse(resposta.getAlternativaEscolhida()), new UsuarioResponse(resposta.getUsuario()), resposta.getDataResposta());
    }
}
