package com.luizmarcelo.apipessoas.service;

import com.luizmarcelo.apipessoas.dto.PessoaEnvioDTO;
import com.luizmarcelo.apipessoas.model.Pessoa;
import com.luizmarcelo.apipessoas.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<Pessoa> salvarEmLote(List<Pessoa> pessoa){
        try{
            return this.pessoaRepository.saveAll(pessoa);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public List<Pessoa> cadastrarPessoas(List<PessoaEnvioDTO> pessoaEnvioList) {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoaEnvioList.forEach(pessoaEnvioDTO -> {
            Pessoa pessoa = new Pessoa();
            BeanUtils.copyProperties(pessoaEnvioDTO, pessoa);
            pessoas.add(pessoa);
        });
        return this.salvarEmLote(pessoas);
    }
}
