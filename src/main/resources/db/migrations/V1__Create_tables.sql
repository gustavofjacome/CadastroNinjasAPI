-- V1 migration para criar as tabelas iniciais

CREATE TABLE tb_missoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_missao VARCHAR(255),
    dificuldade_missao VARCHAR(255)
);

CREATE TABLE tb_cadastro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    idade INT,
    img_url VARCHAR(255),
    missoes_id BIGINT,
    rank VARCHAR(255),
    FOREIGN KEY (missoes_id) REFERENCES tb_missoes(id)
);
