package dev.borriguel.quiz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.borriguel.quiz.dto.request.PerguntaRequest;
import dev.borriguel.quiz.dto.response.PerguntaResponse;
import dev.borriguel.quiz.entity.Pergunta;
import dev.borriguel.quiz.service.PerguntaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pergunta")
@RequiredArgsConstructor
@Tag(name = "Pergunta", description = "Operações de gerenciamento de perguntas")
public class PerguntaController {
    private final PerguntaService perguntaService;

    @PostMapping("/quiz/{idQuiz}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar uma nova pergunta a um quiz")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pergunta criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Quiz não encontrado")
    })
    public PerguntaResponse criarPergunta(@PathVariable Long idQuiz, @RequestBody @Valid PerguntaRequest perguntaData) {
        var pergunta = new Pergunta();
        pergunta.setEnunciado(perguntaData.enunciado());
        pergunta.setDificuldade(perguntaData.dificuldade());
        return new PerguntaResponse(perguntaService.adicionar(idQuiz, pergunta));
    }

    @DeleteMapping("/{idPergunta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar uma pergunta de um quiz")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pergunta deletada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Usuario não autorizado a deletar perguntas"),
        @ApiResponse(responseCode = "404", description = "Pergunta não encontrada")
    })
    public void deletarPergunta(@PathVariable Long idPergunta) {
        perguntaService.deletar(idPergunta);
    }

    @GetMapping("/{idPergunta}")
    @Operation(summary = "Buscar uma pergunta pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pergunta encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pergunta não encontrada")
    })
    public PerguntaResponse buscarPorId(@PathVariable Long idPergunta) {
        return new PerguntaResponse(perguntaService.buscarPorId(idPergunta));
    }

    @GetMapping("/quiz/{idQuiz}")
    @Operation(summary = "Buscar todas as perguntas de um quiz")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perguntas encontradas com sucesso"),
        @ApiResponse(responseCode = "404", description = "Quiz não encontrado")
    })
    public List<PerguntaResponse> buscarPorQuiz(@PathVariable Long idQuiz) {
        return perguntaService.buscarPorQuiz(idQuiz).stream().map(PerguntaResponse::new).toList();
    }

    @PutMapping("/{idPergunta}")
    @Operation(summary = "Atualizar uma pergunta pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pergunta atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "403", description = "Usuario não autorizado a atualizar perguntas"),
        @ApiResponse(responseCode = "404", description = "Pergunta não encontrada")
    })
    public PerguntaResponse atualizar(@PathVariable Long idPergunta, @RequestBody @Valid PerguntaRequest perguntaData) {
        var pergunta = new Pergunta();
        pergunta.setEnunciado(perguntaData.enunciado());
        pergunta.setDificuldade(perguntaData.dificuldade());
        return new PerguntaResponse(perguntaService.atualizar(idPergunta, pergunta));
    }
}
