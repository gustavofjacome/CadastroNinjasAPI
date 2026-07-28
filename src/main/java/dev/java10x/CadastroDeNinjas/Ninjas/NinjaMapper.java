package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO dto) {
        NinjaModel model = new NinjaModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setIdade(dto.getIdade());
        model.setImg_url(dto.getImg_url());
        model.setRank(dto.getRank());
        model.setMissoes(dto.getMissoes());
        return model;
    }

    public NinjaDTO map(NinjaModel model) {
        NinjaDTO dto = new NinjaDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setIdade(model.getIdade());
        dto.setImg_url(model.getImg_url());
        dto.setRank(model.getRank());
        dto.setMissoes(model.getMissoes());
        return dto;
    }
}
