package dev.borriguel.quiz.service;

import dev.borriguel.quiz.entity.Usuario;
import dev.borriguel.quiz.dto.request.UsuarioRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Usuario buscarPorId(Long id);
    Usuario buscarPorUsername(String username);
    Page<Usuario> buscarTodos(Pageable pageable);
    void excluir(Long id);
    Usuario atualizar(Long id, UsuarioRequest usuario);
    Usuario usuarioAtual();

}
