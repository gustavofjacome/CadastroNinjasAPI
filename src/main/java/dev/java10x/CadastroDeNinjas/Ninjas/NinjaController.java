package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ninjas")
public class NinjaController {
// mapeia rotas
    //@GetMapping serve para mostrar algo na tela GET
    @GetMapping("/boasvindas") // pega informações, nesse caso pega o metodo boasVindas
     public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota:";
     }

     //adicionar ninja (Create)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    //mostrar todos os ninjas (read)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas(){
        return "Mostrar Ninjas";
    }

    //mostrar ninja por id (read)
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorId(){
        return "Mostrar Ninja por ID";
    }

    // alterar dados do ninja (update)
   @PutMapping("/alterarID")
   public String alterarNinjaPorId(){
        return "Alterar Ninja por id";
   }

    //deletar ninja (delete)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){
        return "Ninja deletado por id";
    }
}
