package dev.borriguel.quiz.service;

import dev.borriguel.quiz.entity.Pergunta;

import java.util.List;

public interface PerguntaService {
    Pergunta adicionar(Long idQuiz, Pergunta pergunta);
    void salvar(Pergunta pergunta);
    void deletar(Long idPergunta);
    Pergunta buscarPorId(Long idPergunta);
    List<Pergunta> buscarPorQuiz(Long idQuiz);
    Pergunta atualizar(Long idPergunta, Pergunta pergunta);
}
