package com.luizmarcelo.apipessoas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemEnvioDTO {
    private String assunto;

    private String conteudo;

    private boolean urgencia;

}
