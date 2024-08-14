package dev.borriguel.quiz.controller;

import dev.borriguel.quiz.dto.request.RespostaRequest;
import dev.borriguel.quiz.dto.response.RespostaResponse;
import dev.borriguel.quiz.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/respostas")
@RequiredArgsConstructor
@Tag(name = "Resposta", description = "Operações de gerenciamento de respostas")
public class RespostaController {

    private final RespostaService respostaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar uma nova resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resposta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "409", description = "Pergunta já respondida")
    })
    public RespostaResponse salvar(@RequestBody RespostaRequest respostaData) {
        var resposta = respostaService.salvar(respostaData);
        return new RespostaResponse(resposta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma resposta pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Resposta não encontrada")
    })
    public RespostaResponse buscarPorId(@PathVariable Long id) {
        var resposta = respostaService.buscarPorId(id);
        return new RespostaResponse(resposta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir uma resposta pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Resposta deletada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuario não autorizado a deletar respostas"),
            @ApiResponse(responseCode = "404", description = "Resposta não encontrada")
    })
    public void deletar(@PathVariable Long id) {
        respostaService.excluir(id);
    }

    @DeleteMapping("/tudo/{idQuiz}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir todas as respostas pelo ID do quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Respostas deletadas com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuario não autorizado a deletar respostas"),
            @ApiResponse(responseCode = "404", description = "Respostas não encontradas")
    })
    public void deletarTodos(@PathVariable Long idQuiz, @RequestParam Long idUsuario) {
        respostaService.excluirPorQuiz(idQuiz, idUsuario);
    }

}
