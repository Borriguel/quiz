package dev.borriguel.quiz.service.impl;

import dev.borriguel.quiz.dto.response.ResultadoPerguntaResponse;
import dev.borriguel.quiz.dto.response.ResultadoQuizResponse;
import dev.borriguel.quiz.entity.Resposta;
import dev.borriguel.quiz.entity.Role;
import dev.borriguel.quiz.entity.Usuario;
import dev.borriguel.quiz.exception.AutorizacaoException;
import dev.borriguel.quiz.exception.RespostaNaoEncontradaException;
import dev.borriguel.quiz.repository.RespostaRepository;
import dev.borriguel.quiz.service.ResultadoQuizService;
import dev.borriguel.quiz.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultadoQuizServiceImpl implements ResultadoQuizService {
    private final RespostaRepository respostaRepository;
    private final UsuarioService usuarioService;

    @Override
    public ResultadoQuizResponse calcularResultado(Long quizId, Long usuarioId) {
        var respostas = respostaRepository.findByQuizIdAndUsuarioId(quizId, usuarioId);
        if (respostas.isEmpty()) throw new RespostaNaoEncontradaException();
        validarUsuarioResposta(respostas.getFirst());
        log.info("Calculando resultado do Quiz ID: {} para Usuário ID: {}", quizId, usuarioId);

        long totalPerguntas = contarPerguntas(respostas);
        long respostasCorretas = contarRespostasCorretas(respostas);

        List<ResultadoPerguntaResponse> perguntasCorretas = respostas.stream()
                .filter(resposta -> resposta.getAlternativaEscolhida().getCorreta())
                .map(ResultadoPerguntaResponse::new)
                .toList();

        List<ResultadoPerguntaResponse> perguntasIncorretas = respostas.stream()
                .filter(resposta -> !resposta.getAlternativaEscolhida().getCorreta())
                .map(ResultadoPerguntaResponse::new)
                .toList();

        double porcentagemAcertos = ((double) respostasCorretas / totalPerguntas) * 100;
        return new ResultadoQuizResponse(totalPerguntas, respostasCorretas, perguntasCorretas, perguntasIncorretas, porcentagemAcertos);
    }

    private void validarUsuarioResposta(Resposta resposta) {
        var usuarioAtual = usuarioService.usuarioAtual();
        if (!usuarioAutorizadoVerResultado(usuarioAtual, resposta))
            throw new AutorizacaoException();
    }

    private boolean usuarioAutorizadoVerResultado(Usuario usuarioAtual, Resposta resposta) {
        return Objects.equals(usuarioAtual, resposta.getUsuario()) || usuarioAtual.getCargo().stream()
                .anyMatch(cargo -> cargo.getNome() == Role.ROLE_ADMIN || cargo.getNome() == Role.ROLE_MODERADOR);
    }

    private long contarPerguntas(List<Resposta> respostas) {
        return respostas.stream()
                .map(Resposta::getPergunta)
                .distinct()
                .count();
    }

    private long contarRespostasCorretas(List<Resposta> respostas) {
        return respostas.stream()
                .filter(resposta -> resposta.getAlternativaEscolhida().getCorreta())
                .count();
    }
}