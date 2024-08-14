package dev.borriguel.quiz.controller;

import dev.borriguel.quiz.dto.request.AlternativaRequest;
import dev.borriguel.quiz.dto.response.AlternativaResponse;
import dev.borriguel.quiz.entity.Alternativa;
import dev.borriguel.quiz.service.AlternativaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alternativa")
@Tag(name = "Alternativa", description = "Operações de gerenciamento de alternativas")
public class AlternativaController {
    private final AlternativaService alternativaService;

    @PostMapping("/pergunta/{idPergunta}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar uma nova alternativa a uma pergunta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Alternativa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "403", description = "Usuário não autorizado a criar alternativas"),
        @ApiResponse(responseCode = "404", description = "Pergunta não encontrada")
    })
    public AlternativaResponse adicionarAlternativa(@PathVariable Long idPergunta, @RequestBody @Valid AlternativaRequest alternativaData) {
        var alternativa = new Alternativa();
        alternativa.setCorreta(alternativaData.correta());
        alternativa.setProposicao(alternativaData.proposicao());
        return new AlternativaResponse(alternativaService.adicionar(idPergunta, alternativa));
    }

    @PutMapping("/{idAlternativa}")
    @Operation(summary = "Atualizar uma alternativa pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alternativa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "403", description = "Usuário não autorizado a atualizar alternativas"),
        @ApiResponse(responseCode = "404", description = "Alternativa não encontrada")
    })
    public AlternativaResponse atualizar(@PathVariable Long idAlternativa, @RequestBody @Valid AlternativaRequest alternativaData) {
        var alternativa = new Alternativa();
        alternativa.setProposicao(alternativaData.proposicao());
        alternativa.setCorreta(alternativaData.correta());
        return new AlternativaResponse(alternativaService.atualizar(idAlternativa, alternativa));
    }

    @DeleteMapping("/{idAlternativa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover uma alternativa pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Alternativa deletada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Usuário não autorizado a deletar alternativas"),
        @ApiResponse(responseCode = "404", description = "Alternativa não encontrada")
    })
    public void deletarAlternativa(@PathVariable Long idAlternativa) {
        alternativaService.deletar(idAlternativa);
    }
}