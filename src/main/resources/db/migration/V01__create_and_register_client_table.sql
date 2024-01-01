CREATE TABLE clientes (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(60),
	fone VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO clientes (nome, email, fone) values ('Adriano', null, null);
INSERT INTO clientes (nome, email, fone) values ('Alessandra', 'ale.ssandra@email.com', '1111111111');
INSERT INTO clientes (nome, email, fone) values ('Karla', 'karlina@email.com', null);
INSERT INTO clientes (nome, email, fone) values ('Roberto', null, '222222222');
INSERT INTO clientes (nome, email, fone) values ('Gustavo', 'gustavo@email.com', '333333333');