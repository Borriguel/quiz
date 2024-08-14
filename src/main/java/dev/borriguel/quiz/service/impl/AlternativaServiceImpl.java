package dev.borriguel.quiz.service.impl;

import org.springframework.stereotype.Service;

import dev.borriguel.quiz.entity.Alternativa;
import dev.borriguel.quiz.exception.AlternativaNaoEncontradaException;
import dev.borriguel.quiz.repository.AlternativaRepository;
import dev.borriguel.quiz.service.AlternativaService;
import dev.borriguel.quiz.service.PerguntaService;
import dev.borriguel.quiz.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlternativaServiceImpl implements AlternativaService {
    private final AlternativaRepository alternativaRepository;
    private final PerguntaService perguntaService;
    private final QuizService quizService;

    @Override
    @Transactional
    public Alternativa adicionar(Long idPergunta, Alternativa alternativa) {
        var pergunta = perguntaService.buscarPorId(idPergunta);
        var quiz = pergunta.getQuiz();
        quizService.validarUsuarioCriadorDoQuiz(quiz);

        alternativa.setPergunta(pergunta);
        salvar(alternativa);

        log.info("Alternativa ID: {} adicionada à Pergunta ID: {}.", alternativa.getId(), pergunta.getId());
        pergunta.getAlternativas().add(alternativa);
        perguntaService.salvar(pergunta);

        return alternativa;
    }

    @Override
    @Transactional
    public void salvar(Alternativa alternativa) {
        alternativaRepository.save(alternativa);
    }

    @Override
    @Transactional
    public Alternativa atualizar(Long idAlternativa, Alternativa alternativa) {
        var alternativaExistente = buscarPorId(idAlternativa);
        quizService.validarUsuarioCriadorDoQuiz(alternativaExistente.getPergunta().getQuiz());

        alternativaExistente.setProposicao(alternativa.getProposicao());
        alternativaExistente.setCorreta(alternativa.getCorreta());
        log.info("Atualizando Alternativa ID: {}", alternativa.getId());
        salvar(alternativaExistente);

        return alternativaExistente;
    }

    @Override
    public Alternativa buscarPorId(Long idAlternativa) {
        return alternativaRepository.findById(idAlternativa).orElseThrow(AlternativaNaoEncontradaException::new);
    }

    @Override
    @Transactional
    public void deletar(Long idAlternativa) {
        var alternativa = buscarPorId(idAlternativa);
        var quiz = alternativa.getPergunta().getQuiz();
        quizService.validarUsuarioCriadorDoQuiz(quiz);

        log.info("Deletando Alternativa ID: {}", idAlternativa);
        alternativaRepository.deleteById(alternativa.getId());
    }
}
