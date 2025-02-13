package com.luizmarcelo.apipessoas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmailEnvioRequestDTO {

    private MensagemEnvioDTO mensagemEnvio;

    private List<PessoaEnvioDTO> pessoaEnvioList = new ArrayList<>();


}
