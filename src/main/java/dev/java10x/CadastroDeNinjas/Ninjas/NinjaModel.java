package dev.java10x.CadastroDeNinjas.Ninjas;


import dev.java10x.CadastroDeNinjas.Missoes.MissaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // transforma uma classe em uma entidade do banco de dados
@Table(name = "tb_cadastro") // convensao = tb_nome
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // nao precisa estar no contrutor, pois gera automaticamente
    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    @ManyToOne // um ninja tem uma unica missao
    @JoinColumn(name = "missoes_id") //Foreing Key ou chave estrangeira
    private MissaoModel missoes;


}
