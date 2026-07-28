package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
@Tag(name = "Ninjas", description = "Endpoints para gerenciamento de ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas-vindas", description = "Retorna uma mensagem de boas-vindas")
    public String boasVindas() {
        return "Essa e minha primeira mensagem nessa rota:";
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo ninja", description = "Cria um novo ninja com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados invalidos")
    public ResponseEntity<NinjaDTO> criarNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNinja);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os ninjas", description = "Retorna a lista completa de ninjas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<NinjaDTO>> mostrarTodosOsNinjas() {
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Buscar ninja por ID", description = "Retorna um ninja especifico pelo ID")
    @ApiResponse(responseCode = "200", description = "Ninja encontrado")
    @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    public ResponseEntity<NinjaDTO> mostrarNinjaPorId(
            @Parameter(description = "ID do ninja", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(ninjaService.listarNinjaId(id));
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Alterar ninja por ID", description = "Atualiza os dados de um ninja existente")
    @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    public ResponseEntity<NinjaDTO> alterarNinjaPorId(
            @Parameter(description = "ID do ninja", required = true)
            @PathVariable Long id,
            @RequestBody NinjaDTO ninjaDTO) {
        return ResponseEntity.ok(ninjaService.atualizarNinja(id, ninjaDTO));
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar ninja por ID", description = "Remove um ninja do sistema")
    @ApiResponse(responseCode = "204", description = "Ninja deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    public ResponseEntity<Void> deletarNinjaPorId(
            @Parameter(description = "ID do ninja", required = true)
            @PathVariable Long id) {
        ninjaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
