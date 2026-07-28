package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
@Tag(name = "Missoes", description = "Endpoints para gerenciamento de missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as missoes", description = "Retorna a lista completa de missoes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<MissaoModel>> listarTodasMissoes() {
        return ResponseEntity.ok(missaoService.listarMissoes());
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Buscar missao por ID", description = "Retorna uma missao especifica pelo ID")
    @ApiResponse(responseCode = "200", description = "Missao encontrada")
    @ApiResponse(responseCode = "404", description = "Missao nao encontrada")
    public ResponseEntity<MissaoModel> listarMissaoPorId(
            @Parameter(description = "ID da missao", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(missaoService.listarMissaoId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar uma nova missao", description = "Cria uma nova missao com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Missao criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados invalidos")
    public ResponseEntity<MissaoModel> criarMissao(@RequestBody MissaoModel missao) {
        MissaoModel novaMissao = missaoService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMissao);
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Alterar missao por ID", description = "Atualiza os dados de uma missao existente")
    @ApiResponse(responseCode = "200", description = "Missao atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Missao nao encontrada")
    public ResponseEntity<MissaoModel> alterarMissao(
            @Parameter(description = "ID da missao", required = true)
            @PathVariable Long id,
            @RequestBody MissaoModel missaoAtualizada) {
        return ResponseEntity.ok(missaoService.alterarMissao(id, missaoAtualizada));
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar missao por ID", description = "Remove uma missao do sistema")
    @ApiResponse(responseCode = "204", description = "Missao deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Missao nao encontrada")
    public ResponseEntity<Void> deletarMissao(
            @Parameter(description = "ID da missao", required = true)
            @PathVariable Long id) {
        missaoService.deletarMissao(id);
        return ResponseEntity.noContent().build();
    }
}
