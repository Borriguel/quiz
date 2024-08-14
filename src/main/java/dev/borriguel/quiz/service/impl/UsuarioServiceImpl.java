package dev.borriguel.quiz.service.impl;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.borriguel.quiz.dto.request.UsuarioRequest;
import dev.borriguel.quiz.entity.Role;
import dev.borriguel.quiz.entity.Usuario;
import dev.borriguel.quiz.exception.AutorizacaoException;
import dev.borriguel.quiz.exception.EmailJaEmUsoException;
import dev.borriguel.quiz.exception.UsernameJaEmUsoException;
import dev.borriguel.quiz.exception.UsuarioNaoEncontradoException;
import dev.borriguel.quiz.repository.UsuarioRepository;
import dev.borriguel.quiz.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Override
    public Page<Usuario> buscarTodos(Pageable pageable) {
        log.info("Buscando todos os usuários com paginação");
        return usuarioRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        var usuario = buscarPorId(id);
        validarUsuario(usuario);
        log.info("Deletando Usuario ID: {}", id);
        usuarioRepository.delete(usuario);
    }

    @Override
    @Transactional
    public Usuario atualizar(Long id, UsuarioRequest usuarioRequest) {
        var usuarioExistente = buscarPorId(id);
        validarUsuario(usuarioExistente);
        verificarUsernameEmailEmUso(usuarioRequest, usuarioExistente);

        atualizarCampos(usuarioExistente, usuarioRequest);
        log.info("Atualizando Usuario ID: {}", id);
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public Usuario usuarioAtual() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return buscarPorUsername(username);
    }

    private void verificarUsernameEmailEmUso(UsuarioRequest usuarioNovo, Usuario usuarioAntigo) {
        if (usuarioNovo.username() != null && usuarioRepository.existsByUsername(usuarioNovo.username()) && !usuarioAntigo.getUsername().equalsIgnoreCase(usuarioNovo.username()))
            throw new UsernameJaEmUsoException();

        if (usuarioNovo.email() != null && usuarioRepository.existsByEmail(usuarioNovo.email()) && !usuarioAntigo.getEmail().equalsIgnoreCase(usuarioNovo.email()))
            throw new EmailJaEmUsoException();

    }

    private void validarUsuario(Usuario usuario) {
        var usuarioAtual = usuarioAtual();
        if (!usuarioAutorizadoParaModificar(usuarioAtual, usuario)) {
            throw new AutorizacaoException();
        }
    }

    private boolean usuarioAutorizadoParaModificar(Usuario usuarioAtual, Usuario usuario) {
        return Objects.equals(usuarioAtual, usuario) || usuarioAtual.getCargo().stream()
                .anyMatch(cargo -> cargo.getNome() == Role.ROLE_ADMIN || cargo.getNome() == Role.ROLE_MODERADOR);
    }

    private void atualizarCampos(Usuario usuarioExistente, UsuarioRequest usuarioRequest) {
        if (usuarioRequest.username() != null) usuarioExistente.setUsername(usuarioRequest.username());
        if (usuarioRequest.nome() != null) usuarioExistente.setNome(usuarioRequest.nome());
        if (usuarioRequest.email() != null) usuarioExistente.setEmail(usuarioRequest.email());
        if (usuarioRequest.dataNascimento() != null)
            usuarioExistente.setDataNascimento(usuarioRequest.dataNascimento());
        if (usuarioRequest.senha() != null) usuarioExistente.setSenha(passwordEncoder.encode(usuarioRequest.senha()));
    }
}
