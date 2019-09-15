DROP TABLE IF EXISTS pessoa; -- Esta tabela caiu em desuso
DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS atendente;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario(
	id_usuario INT NOT NULL AUTO_INCREMENT,
	cpf VARCHAR(11) NOT NULL,
	nome VARCHAR(32) NOT NULL,
	data_nascimento DATE,
	senha VARCHAR(32) NOT NULL,
	PRIMARY KEY (id_usuario),
	UNIQUE (cpf)
) ENGINE=InnoDB;

CREATE TABLE paciente(
	id_usuario INT NOT NULL,
	end_logradouro VARCHAR(32),
	end_numero VARCHAR(8),
	end_complemento VARCHAR(16),
	end_municipio VARCHAR(32),
	telefone1 VARCHAR(16),
	telefone2 VARCHAR(16),
	PRIMARY KEY (id_usuario)
) ENGINE=InnoDB;

CREATE TABLE medico(
	id_usuario INT NOT NULL,
	registro VARCHAR(16) NOT NULL,
	especialidade VARCHAR(16) NOT NULL,
	PRIMARY KEY (id_usuario)
) ENGINE=InnoDB;

CREATE TABLE atendente(
	id_usuario INT NOT NULL,
	administrador BOOL NOT NULL,
	PRIMARY KEY (id_usuario)
) ENGINE=InnoDB;

CREATE TABLE consulta(
	id_consulta INT NOT NULL AUTO_INCREMENT,
	data_hora DATETIME NOT NULL,
	local VARCHAR(32),
	id_paciente INT NOT NULL,
	id_medico INT NOT NULL,
	PRIMARY KEY(id_consulta)
) ENGINE=InnoDB;

ALTER TABLE paciente
ADD FOREIGN KEY (id_usuario)
REFERENCES usuario(id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE medico
ADD FOREIGN KEY (id_usuario)
REFERENCES usuario(id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE atendente
ADD FOREIGN KEY (id_usuario)
REFERENCES usuario(id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE consulta
ADD FOREIGN KEY(id_paciente)
REFERENCES paciente(id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE consulta
ADD FOREIGN KEY(id_medico)
REFERENCES medico(id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE;

