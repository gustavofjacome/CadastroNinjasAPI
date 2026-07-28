package dev.java10x.CadastroDeNinjas;

import dev.java10x.CadastroDeNinjas.Missoes.Dificuldade;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoModel;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoRepository;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoService;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.Rank;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    private final NinjaService ninjaService;
    private final MissaoService missaoService;
    private final MissaoRepository missaoRepository;

    public WebController(NinjaService ninjaService, MissaoService missaoService, MissaoRepository missaoRepository) {
        this.ninjaService = ninjaService;
        this.missaoService = missaoService;
        this.missaoRepository = missaoRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        List<MissaoModel> missoes = missaoService.listarMissoes();
        model.addAttribute("totalNinjas", ninjas.size());
        model.addAttribute("totalMissoes", missoes.size());
        model.addAttribute("ninjas", ninjas);
        return "index";
    }

    // ==================== NINJAS ====================

    @GetMapping("/ninjas")
    public String listarNinjas(Model model) {
        model.addAttribute("ninjas", ninjaService.listarNinjas());
        return "ninjas/listar";
    }

    @GetMapping("/ninjas/cadastrar")
    public String cadastrarNinja(Model model) {
        model.addAttribute("ninjaDTO", new NinjaDTO());
        model.addAttribute("missoes", missaoService.listarMissoes());
        model.addAttribute("ranks", Rank.values());
        return "ninjas/cadastrar";
    }

    @PostMapping("/ninjas/cadastrar")
    public String salvarNinja(@RequestParam String nome,
                              @RequestParam String email,
                              @RequestParam int idade,
                              @RequestParam String img_url,
                              @RequestParam Rank rank,
                              @RequestParam(value = "missaoId", required = false) Long missaoId,
                              RedirectAttributes redirectAttributes) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome(nome);
        ninjaDTO.setEmail(email);
        ninjaDTO.setIdade(idade);
        ninjaDTO.setImg_url(img_url);
        ninjaDTO.setRank(rank);

        if (missaoId != null) {
            MissaoModel missao = missaoRepository.findById(missaoId).orElse(null);
            ninjaDTO.setMissoes(missao);
        }

        ninjaService.criarNinja(ninjaDTO);
        redirectAttributes.addFlashAttribute("sucesso", "Ninja criado com sucesso!");
        return "redirect:/web/ninjas";
    }

    @GetMapping("/ninjas/editar/{id}")
    public String editarNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaId(id);
        model.addAttribute("ninjaDTO", ninja);
        model.addAttribute("missoes", missaoService.listarMissoes());
        model.addAttribute("ranks", Rank.values());
        return "ninjas/cadastrar";
    }

    @PostMapping("/ninjas/editar/{id}")
    public String atualizarNinja(@PathVariable Long id,
                                 @RequestParam String nome,
                                 @RequestParam String email,
                                 @RequestParam int idade,
                                 @RequestParam String img_url,
                                 @RequestParam Rank rank,
                                 @RequestParam(value = "missaoId", required = false) Long missaoId,
                                 RedirectAttributes redirectAttributes) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome(nome);
        ninjaDTO.setEmail(email);
        ninjaDTO.setIdade(idade);
        ninjaDTO.setImg_url(img_url);
        ninjaDTO.setRank(rank);

        if (missaoId != null) {
            MissaoModel missao = missaoRepository.findById(missaoId).orElse(null);
            ninjaDTO.setMissoes(missao);
        }

        ninjaService.atualizarNinja(id, ninjaDTO);
        redirectAttributes.addFlashAttribute("sucesso", "Ninja atualizado com sucesso!");
        return "redirect:/web/ninjas";
    }

    @GetMapping("/ninjas/deletar/{id}")
    public String deletarNinja(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ninjaService.deletarPorId(id);
        redirectAttributes.addFlashAttribute("sucesso", "Ninja deletado com sucesso!");
        return "redirect:/web/ninjas";
    }

    // ==================== MISSOES ====================

    @GetMapping("/missoes")
    public String listarMissoes(Model model) {
        model.addAttribute("missoes", missaoService.listarMissoes());
        return "missoes/listar";
    }

    @GetMapping("/missoes/cadastrar")
    public String cadastrarMissao(Model model) {
        model.addAttribute("dificuldades", Dificuldade.values());
        return "missoes/cadastrar";
    }

    @PostMapping("/missoes/cadastrar")
    public String salvarMissao(@RequestParam String nomeMissao,
                               @RequestParam Dificuldade dificuldadeMissao,
                               RedirectAttributes redirectAttributes) {
        MissaoModel missao = new MissaoModel();
        missao.setNomeMissao(nomeMissao);
        missao.setDificuldadeMissao(dificuldadeMissao);
        missaoService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("sucesso", "Missao criada com sucesso!");
        return "redirect:/web/missoes";
    }

    @GetMapping("/missoes/editar/{id}")
    public String editarMissao(@PathVariable Long id, Model model) {
        MissaoModel missao = missaoService.listarMissaoId(id);
        model.addAttribute("missao", missao);
        model.addAttribute("dificuldades", Dificuldade.values());
        return "missoes/cadastrar";
    }

    @PostMapping("/missoes/editar/{id}")
    public String atualizarMissao(@PathVariable Long id,
                                  @RequestParam String nomeMissao,
                                  @RequestParam Dificuldade dificuldadeMissao,
                                  RedirectAttributes redirectAttributes) {
        MissaoModel missao = new MissaoModel();
        missao.setNomeMissao(nomeMissao);
        missao.setDificuldadeMissao(dificuldadeMissao);
        missaoService.alterarMissao(id, missao);
        redirectAttributes.addFlashAttribute("sucesso", "Missao atualizada com sucesso!");
        return "redirect:/web/missoes";
    }

    @GetMapping("/missoes/deletar/{id}")
    public String deletarMissao(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        missaoService.deletarMissao(id);
        redirectAttributes.addFlashAttribute("sucesso", "Missao deletada com sucesso!");
        return "redirect:/web/missoes";
    }
}
