package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    //GET - serve para dar para o usuario, damos ao usuario a lista de missao
    @GetMapping("/listar")
    public String listarMissao(){
        return "missoes listadas com sucesso";
    }



    //POST - Recebe dados do usuario, nesse caso ele cria missao com infos que o usuario vai dar
    @PostMapping("/criar")  //   missoes/criar (ele concatena com o requestmapping)
    public String criarMissao(){
        return "Missao criada com sucesso";
    }


    //PUT - usuario manda requisisao para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "missao alterada com sucesso";
    }

    //DELETE - usuario manda requisisao para deletar as missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "missao deletada com sucesso";
    }

}
