package dev.borriguel.quiz.dto.response;

import java.util.List;

public record ResultadoQuizResponse(long totalPerguntas, long respostasCorretas,
                                    List<ResultadoPerguntaResponse> perguntasCorretas, List<ResultadoPerguntaResponse> perguntasIncorretas, double porcentagemAcertos) {
}
