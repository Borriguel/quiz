package dev.borriguel.quiz.service.impl;

import dev.borriguel.quiz.entity.Pergunta;
import dev.borriguel.quiz.exception.PerguntaNaoEncontradaException;
import dev.borriguel.quiz.repository.PerguntaRepository;
import dev.borriguel.quiz.service.PerguntaService;
import dev.borriguel.quiz.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PerguntaServiceImpl implements PerguntaService {
    private final PerguntaRepository perguntaRepository;
    private final QuizService quizService;

    @Override
    @Transactional
    public Pergunta adicionar(Long idQuiz, Pergunta pergunta) {
        var quiz = quizService.buscarPorId(idQuiz);
        quizService.validarUsuarioCriadorDoQuiz(quiz);

        pergunta.setQuiz(quiz);
        salvar(pergunta);

        quiz.getPerguntas().add(pergunta);
        quizService.salvar(quiz);
        log.info("Pergunta ID: {} adicionada ao quiz ID: {}.", pergunta.getId(), quiz.getId());
        return pergunta;
    }

    @Override
    @Transactional
    public void salvar(Pergunta pergunta) {
        perguntaRepository.save(pergunta);
    }

    @Override
    @Transactional
    public void deletar(Long idPergunta) {
        var pergunta = buscarPorId(idPergunta);
        var quiz = pergunta.getQuiz();
        quizService.validarUsuarioCriadorDoQuiz(quiz);
        log.info("Deletando Pergunta ID: {}", idPergunta);
        perguntaRepository.deleteById(pergunta.getId());
    }

    @Override
    public Pergunta buscarPorId(Long idPergunta) {
        return perguntaRepository.findById(idPergunta).orElseThrow(PerguntaNaoEncontradaException::new);
    }

    @Override
    public List<Pergunta> buscarPorQuiz(Long idQuiz) {
        log.info("Buscando todas as perguntas do Quiz ID: {} com paginação", idQuiz);
        return perguntaRepository.findByQuizId(idQuiz);
    }

    @Override
    @Transactional
    public Pergunta atualizar(Long idPergunta, Pergunta pergunta) {
        var perguntaExistente = buscarPorId(idPergunta);
        var quiz = perguntaExistente.getQuiz();
        quizService.validarUsuarioCriadorDoQuiz(quiz);
        if (pergunta.getEnunciado() != null) {
            perguntaExistente.setEnunciado(pergunta.getEnunciado());
        }
        if (pergunta.getAlternativas() != null) {
            perguntaExistente.setDificuldade(pergunta.getDificuldade());
        }
        salvar(perguntaExistente);
        log.info("Atualizando Pergunta ID: {}", pergunta.getId());
        return perguntaExistente;
    }
}
