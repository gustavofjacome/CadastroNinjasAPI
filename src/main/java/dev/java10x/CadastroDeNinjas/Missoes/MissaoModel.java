package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nomeMissao;
   private Dificuldade dificuldadeMissao;

   @OneToMany(mappedBy = "missoes") // uma missao pode ter vários ninjas
   private List<NinjaModel> ninjas;

}
