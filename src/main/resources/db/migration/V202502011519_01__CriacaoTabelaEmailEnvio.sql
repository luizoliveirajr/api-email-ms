CREATE TABLE email_envio
(
    id               INT AUTO_INCREMENT NOT NULL,
    data_criacao     datetime           NULL,
    data_atualizacao datetime           NULL,
    pessoa_id        INT                NOT NULL,
    mensagem_id      INT                NOT NULL,
    status           BOOLEAN             NOT NULL,
    CONSTRAINT pk_emailenvio PRIMARY KEY (id)
);

ALTER TABLE email_envio
    ADD CONSTRAINT FK_EMAILENVIO_ON_MENSAGEM FOREIGN KEY (mensagem_id) REFERENCES mensagem (id);

ALTER TABLE email_envio
    ADD CONSTRAINT FK_EMAILENVIO_ON_PESSOA FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);