package dev.borriguel.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.borriguel.quiz.dto.request.LoginRequest;
import dev.borriguel.quiz.dto.request.UsuarioRequest;
import dev.borriguel.quiz.dto.response.LoginResponse;
import dev.borriguel.quiz.dto.response.UsuarioResponse;
import dev.borriguel.quiz.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Operações de autenticação e registro de usuários")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar um novo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "409", description = "Usuário já existe")
    })
    public UsuarioResponse register(@RequestBody @Valid UsuarioRequest dadosRegistro) {
        return new UsuarioResponse(authService.registrar(dadosRegistro));
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login na aplicação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public LoginResponse login(@RequestBody @Valid LoginRequest dadosLogin) {
        return authService.login(dadosLogin);
    }
}