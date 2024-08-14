package dev.borriguel.quiz.service.impl;

import dev.borriguel.quiz.entity.Quiz;
import dev.borriguel.quiz.entity.Role;
import dev.borriguel.quiz.entity.Usuario;
import dev.borriguel.quiz.exception.AutorizacaoException;
import dev.borriguel.quiz.exception.QuizNaoEncontradoException;
import dev.borriguel.quiz.repository.QuizRepository;
import dev.borriguel.quiz.service.QuizService;
import dev.borriguel.quiz.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final UsuarioService usuarioService;

    @Override
    @Transactional
    public Quiz salvar(Quiz quiz) {
        var usuario = usuarioService.usuarioAtual();
        quiz.setCriador(usuario);
        quizRepository.save(quiz);
        log.info("Quiz ID: {} criado por {}.", quiz.getId(), usuario.getUsername());
        return quiz;
    }

    @Override
    public Page<Quiz> buscarTodos(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Quiz buscarPorId(Long id) {
        return quizRepository.findById(id).orElseThrow(QuizNaoEncontradoException::new);
    }

    @Override
    @Transactional
    public Quiz atualizar(Long id, Quiz quiz) {
        var quizExistente = buscarPorId(id);
        validarUsuarioCriadorDoQuiz(quizExistente);

        if (quiz.getTitulo() != null) {
            quizExistente.setTitulo(quiz.getTitulo());
        }
        if (quiz.getDescricao() != null) {
            quizExistente.setDescricao(quiz.getDescricao());
        }
        quizRepository.save(quizExistente);
        log.info("Atualizando Quiz ID: {}", quizExistente.getId());
        return quizExistente;
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        var quiz = buscarPorId(id);
        validarUsuarioCriadorDoQuiz(quiz);

        quizRepository.deleteById(quiz.getId());
        log.info("Deletando Quiz ID: {}", quiz.getId());
    }

    @Override
    public void validarUsuarioCriadorDoQuiz(Quiz quiz) {
        var usuarioAtual = usuarioService.usuarioAtual();
        if (!usuarioAutorizadoParaModificarQuiz(usuarioAtual, quiz)) {
            throw new AutorizacaoException();
        }
    }

    private boolean usuarioAutorizadoParaModificarQuiz(Usuario usuarioAtual, Quiz quiz) {
        return Objects.equals(usuarioAtual, quiz.getCriador()) || usuarioAtual.getCargo().stream()
                .anyMatch(cargo -> cargo.getNome() == Role.ROLE_ADMIN || cargo.getNome() == Role.ROLE_MODERADOR);
    }
}
