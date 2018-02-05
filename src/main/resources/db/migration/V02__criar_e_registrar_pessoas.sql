-- Script para criar a tabela PESSOA e inserir as pessoas iniciais
CREATE TABLE pessoa (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    ativo TINYINT(1) NOT NULL,
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(30),
    cep VARCHAR(10),
    cidade VARCHAR(30),
    estado VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES (
	'Thiago Pereira', 1, 'Av. Germano Frank', '1000', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Maria Fernandes', 1, 'Av. Germano Frank', '2000', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'José Pereira', 0, 'Av. Germano Frank', '3000', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Paulo Vitor', 1, 'Av. Germano Frank', '4000', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Felipe Bruno', 1, 'Av. Germano Frank', '1000', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Alencar Júnior', 1, 'Av. Germano Frank', '1500', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Francisco Alves', 1, 'Av. Germano Frank', '1230', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Mariana Barbosa', 0, 'Av. Germano Frank', '1000', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Rebeca Cunha', 1, 'Av. Germano Frank', '1700', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);

INSERT INTO 
	pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
VALUES (
	'Letícia Paiva', 0, 'Av. Germano Frank', '2600', 'Próximo ao terminal', 'Parangaba', '60000000', 'Fortaleza', 'Ceará'
);
