package dev.borriguel.quiz.service;

import dev.borriguel.quiz.dto.request.LoginRequest;
import dev.borriguel.quiz.dto.request.UsuarioRequest;
import dev.borriguel.quiz.dto.response.LoginResponse;
import dev.borriguel.quiz.entity.Usuario;

public interface AuthService {
    Usuario registrar(UsuarioRequest dadosRegistro);

    LoginResponse login(LoginRequest dadosLogin);
}
