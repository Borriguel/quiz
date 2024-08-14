package dev.borriguel.quiz.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import dev.borriguel.quiz.dto.request.QuizRequest;
import dev.borriguel.quiz.dto.response.QuizResponse;
import dev.borriguel.quiz.entity.Quiz;
import dev.borriguel.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/quiz")
@AllArgsConstructor
@Tag(name = "Quiz", description = "Operações de gerenciamento de quizzes")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar um novo quiz")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Quiz criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public QuizResponse salvar(@RequestBody QuizRequest quizData) {
        var quiz = new Quiz();
        quiz.setTitulo(quizData.titulo());
        quiz.setDescricao(quizData.descricao());
        return new QuizResponse(quizService.salvar(quiz));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um quiz pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quiz encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Quiz não encontrado")
    })
    public QuizResponse buscarPorId(@PathVariable Long id) {
        return new QuizResponse(quizService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Buscar todos os quizzes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quizzes encontrados com sucesso")
    })
    public Page<QuizResponse> buscarTodos(@ParameterObject Pageable pageable) {
        return quizService.buscarTodos(pageable).map(QuizResponse::new);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um quiz pelo ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Quiz deletado com sucesso"),
        @ApiResponse(responseCode = "403", description = "Usuario não autorizado a deletar quizzes"),
        @ApiResponse(responseCode = "404", description = "Quiz não encontrado")
    })
    public void excluir(@PathVariable Long id) {
        quizService.excluir(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um quiz pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quiz atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "403", description = "Usuario não autorizado a atualizar quizzes"),
        @ApiResponse(responseCode = "404", description = "Quiz não encontrado")
    })
    public QuizResponse atualizar(@PathVariable Long id, @RequestBody @Valid QuizRequest quizData) {
        var quiz = new Quiz();
        quiz.setTitulo(quizData.titulo());
        quiz.setDescricao(quizData.descricao());
        return new QuizResponse(quizService.atualizar(id, quiz));
    }
}
