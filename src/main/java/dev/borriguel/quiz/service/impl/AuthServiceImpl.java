package dev.borriguel.quiz.service.impl;

import dev.borriguel.quiz.dto.request.LoginRequest;
import dev.borriguel.quiz.dto.request.UsuarioRequest;
import dev.borriguel.quiz.dto.response.LoginResponse;
import dev.borriguel.quiz.entity.Role;
import dev.borriguel.quiz.entity.Usuario;
import dev.borriguel.quiz.exception.CargoNaoEncontradoException;
import dev.borriguel.quiz.exception.EmailJaEmUsoException;
import dev.borriguel.quiz.exception.UsernameJaEmUsoException;
import dev.borriguel.quiz.repository.CargoRepository;
import dev.borriguel.quiz.repository.UsuarioRepository;
import dev.borriguel.quiz.security.jwt.JwtUtils;
import dev.borriguel.quiz.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public Usuario registrar(UsuarioRequest dadosRegistro) {
        if (usuarioRepository.existsByUsername(dadosRegistro.username())) {
            throw new UsernameJaEmUsoException();
        }
        if (usuarioRepository.existsByEmail(dadosRegistro.email())) {
            throw new EmailJaEmUsoException();
        }
        var usuario = new Usuario(dadosRegistro);
        usuario.setSenha(passwordEncoder.encode(dadosRegistro.senha()));
        var cargoUsuario = cargoRepository.findByNome(Role.ROLE_USUARIO).orElseThrow(CargoNaoEncontradoException::new);
        usuario.setCargo(Set.of(cargoUsuario));
        usuarioRepository.save(usuario);
        log.info("Usuário {} registrado com sucesso!", usuario.getUsername());
        return usuario;
    }

    @Override
    public LoginResponse login(LoginRequest dadosLogin) {
        var autenticacao = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dadosLogin.username(), dadosLogin.senha()));
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
        return new LoginResponse(jwtUtils.generateJwtToken(autenticacao));
    }
}
