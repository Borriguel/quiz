package dev.borriguel.quiz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.borriguel.quiz.dto.response.ResultadoQuizResponse;
import dev.borriguel.quiz.service.ResultadoQuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resultado")
@RequiredArgsConstructor
@Tag(name = "Resultado Quiz", description = "Operações de gerenciamento de resultados de quizzes")
public class ResultadoQuizController {

    private final ResultadoQuizService resultadoQuizService;

    @GetMapping("/quiz/{quizId}")
    @Operation(summary = "Calcular o resultado de um quiz para um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resultado calculado com sucesso"),
        @ApiResponse(responseCode = "403", description = "Usuário tentando acessar resultado que não seja dele próprio"),
        @ApiResponse(responseCode = "404", description = "Quiz ou usuário não encontrado")
    })
    public ResultadoQuizResponse calcularResultado(@PathVariable Long quizId, @RequestParam Long usuarioId) {
        return resultadoQuizService.calcularResultado(quizId, usuarioId);
    }
}
