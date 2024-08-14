package dev.borriguel.quiz.service;

import dev.borriguel.quiz.entity.Alternativa;

public interface AlternativaService {
    Alternativa adicionar(Long idPergunta, Alternativa alternativa);
    void salvar(Alternativa alternativa);
    Alternativa atualizar(Long idAlternativa, Alternativa alternativa);
    Alternativa buscarPorId(Long idAlternativa);
    void deletar(Long idAlternativa);
}
