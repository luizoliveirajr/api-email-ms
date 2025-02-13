package com.luizmarcelo.apipessoas.service;

import com.luizmarcelo.apipessoas.dto.MensagemEnvioDTO;
import com.luizmarcelo.apipessoas.model.Mensagem;
import com.luizmarcelo.apipessoas.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public Mensagem salvar(Mensagem mensagem){
        try{
            return this.mensagemRepository.save(mensagem);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    public Mensagem cadastrarMensagem(MensagemEnvioDTO mensagemEnvio) {
        Mensagem mensagem = gerarMensagem(mensagemEnvio);
        return this.salvar(mensagem);
    }

    private Mensagem gerarMensagem(MensagemEnvioDTO mensagemEnvio) {
        Mensagem mensagem = new Mensagem();
        BeanUtils.copyProperties(mensagemEnvio, mensagem);
        return mensagem;
    }
}
