package dev.borriguel.quiz.service.impl;

import dev.borriguel.quiz.dto.request.RespostaRequest;
import dev.borriguel.quiz.entity.Resposta;
import dev.borriguel.quiz.exception.AutorizacaoException;
import dev.borriguel.quiz.exception.PerguntaRespondidaException;
import dev.borriguel.quiz.repository.RespostaRepository;
import dev.borriguel.quiz.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RespostaServiceImpl implements RespostaService {

    private final RespostaRepository respostaRepository;
    private final UsuarioService usuarioService;
    private final QuizService quizService;
    private final PerguntaService perguntaService;
    private final AlternativaService alternativaService;

    @Override
    @Transactional
    public Resposta salvar(RespostaRequest respostaRequest) {
        verificarPerguntaRespondida(respostaRequest.idPergunta());
        var quiz = quizService.buscarPorId(respostaRequest.idQuiz());
        var pergunta = perguntaService.buscarPorId(respostaRequest.idPergunta());
        var alternativa = alternativaService.buscarPorId(respostaRequest.idAlternativa());
        var usuario = usuarioService.usuarioAtual();
        var resposta = new Resposta();
        resposta.setQuiz(quiz);
        resposta.setPergunta(pergunta);
        resposta.setAlternativaEscolhida(alternativa);
        resposta.setUsuario(usuario);
        log.info("Salvando resposta para Usuário ID: {}", usuario.getId());
        return respostaRepository.save(resposta);
    }

    @Override
    public Resposta buscarPorId(Long id) {
        var usuario = usuarioService.usuarioAtual();
        return respostaRepository.findById(id).filter(resposta -> resposta.getUsuario().equals(usuario))
                .orElseThrow(AutorizacaoException::new);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        var resposta = buscarPorId(id);
        log.info("Excluindo Resposta ID: {}", id);
        respostaRepository.delete(resposta);
    }

    @Override
    @Transactional
    public void excluirPorQuiz(Long idQuiz, Long idUsuario) {
        if (!Objects.equals(idUsuario, usuarioService.usuarioAtual().getId())) {
            throw new AutorizacaoException();
        }
        var respostas = respostaRepository.findByQuizIdAndUsuarioId(idQuiz, idUsuario);
        log.info("Respostas excluídas com sucesso para Quiz ID: {} e Usuário ID: {}", idQuiz, idUsuario);
        respostaRepository.deleteAll(respostas);
    }

    private void verificarPerguntaRespondida(Long perguntaId) {
        var usuario = usuarioService.usuarioAtual();
        var respostaOptional = respostaRepository.findByPerguntaIdAndUsuarioId(perguntaId, usuario.getId());
        if (respostaOptional.isPresent()) {
            throw new PerguntaRespondidaException();
        }
    }
}
