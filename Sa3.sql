CREATE DATABASE sa3;

USE sa3;

CREATE TABLE  cliente (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(200),
endereco VARCHAR(200),
modalidade VARCHAR (200)
);

INSERT INTO `sa3`.`cliente` (`id`, `nome`, `endereco`, `modalidade`) 
VALUES ('1', 'Carlos', 'São Paulo, Brasil', '123.456.789-10');

SELECT * FROM cliente;

DROP TABLE Cliente;