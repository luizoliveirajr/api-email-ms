CREATE TABLE pessoa
(
    id               INT AUTO_INCREMENT NOT NULL,
    data_criacao     datetime           NULL,
    data_atualizacao datetime           NULL,
    nome             VARCHAR(255)       NULL,
    email            VARCHAR(255)       NULL,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);
