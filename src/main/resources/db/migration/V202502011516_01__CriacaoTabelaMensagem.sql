CREATE TABLE mensagem
(
    id               INT AUTO_INCREMENT NOT NULL,
    data_criacao     datetime           NULL,
    data_atualizacao datetime           NULL,
    conteudo         VARCHAR(255)       NULL,
    assunto          VARCHAR(255)       NULL,
    urgencia         BOOLEAN             NOT NULL,
    CONSTRAINT pk_mensagem PRIMARY KEY (id)
);