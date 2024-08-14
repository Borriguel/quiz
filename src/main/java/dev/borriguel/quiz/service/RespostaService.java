package dev.borriguel.quiz.service;

import dev.borriguel.quiz.dto.request.RespostaRequest;
import dev.borriguel.quiz.entity.Resposta;

public interface RespostaService {

    Resposta salvar(RespostaRequest respostaRequest);

    Resposta buscarPorId(Long id);

    void excluir(Long id);

    void excluirPorQuiz(Long idQuiz, Long idUsuario);
}
