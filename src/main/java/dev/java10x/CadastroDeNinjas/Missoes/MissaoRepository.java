package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoRepository extends JpaRepository<MissaoModel, Long> {
    // passa a classe que o orm vai analisar e o tipo do id da classe
}
