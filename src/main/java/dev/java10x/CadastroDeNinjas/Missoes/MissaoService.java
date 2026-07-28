package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoModel> listarMissoes() {
        return missaoRepository.findAll();
    }

    public MissaoModel listarMissaoId(Long id) {
        return missaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Missao nao encontrada com ID: " + id));
    }

    public MissaoModel criarMissao(MissaoModel missao) {
        return missaoRepository.save(missao);
    }

    public MissaoModel alterarMissao(Long id, MissaoModel missaoAtualizada) {
        if (!missaoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Missao nao encontrada com ID: " + id);
        }
        missaoAtualizada.setId(id);
        return missaoRepository.save(missaoAtualizada);
    }

    public void deletarMissao(Long id) {
        if (!missaoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Missao nao encontrada com ID: " + id);
        }
        missaoRepository.deleteById(id);
    }
}
