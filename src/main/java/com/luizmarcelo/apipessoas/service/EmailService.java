package com.luizmarcelo.apipessoas.service;

import com.luizmarcelo.apipessoas.dto.EmailEnvioRequestDTO;
import com.luizmarcelo.apipessoas.model.EmailEnvio;
import com.luizmarcelo.apipessoas.model.Mensagem;
import com.luizmarcelo.apipessoas.model.Pessoa;
import com.luizmarcelo.apipessoas.repository.EmailEnvioRepository;
import com.luizmarcelo.apipessoas.service.rabbitmq.EmailProducer;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final PessoaService pessoaService;
    private final MensagemService mensagemService;
    private final EmailEnvioRepository emailEnvioRepository;
    private final EmailProducer emailProducer;

    private final JavaMailSender mailSender;
    private final EmailTemplateService emailTemplateService;

    public List<EmailEnvio> salvarEmLote(List<EmailEnvio> envios) {
        try{
            return this.emailEnvioRepository.saveAll(envios);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public EmailEnvio salvar(EmailEnvio emailEnvio) {
        try{
            return this.emailEnvioRepository.save(emailEnvio);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public void cadastrarEmail(EmailEnvioRequestDTO dto) {
        Mensagem mensagem = this.mensagemService.cadastrarMensagem(dto.getMensagemEnvio());
        List<Pessoa> pessoas = this.pessoaService.cadastrarPessoas(dto.getPessoaEnvioList());
        List<EmailEnvio> emailEnvios = cadastrarEmailsEnvio(mensagem, pessoas);
        emailEnvios.forEach(this.emailProducer::enviarEmailParaFila);
    }

    private List<EmailEnvio> cadastrarEmailsEnvio(Mensagem mensagem, List<Pessoa> pessoas) {
        List<EmailEnvio> emailEnvios = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            EmailEnvio emailEnvio = new EmailEnvio();
            emailEnvio.setMensagem(mensagem);
            emailEnvio.setPessoa(pessoa);
            emailEnvio.setStatus(false);
            emailEnvios.add(emailEnvio);
        });
        return this.salvarEmLote(emailEnvios);
    }


    public void enviarEmail(EmailEnvio emailEnvio) {
        try {
            Map<String, Object> variaveis = new HashMap<>();
            Mensagem mensagem = emailEnvio.getMensagem();
            Pessoa pessoa = emailEnvio.getPessoa();

            variaveis.put("nome", pessoa.getNome());
            variaveis.put("mensagem", mensagem.getConteudo());

            String conteudoHtml = emailTemplateService.processarTemplate("email-template", variaveis);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(pessoa.getEmail());
            helper.setSubject(mensagem.getAssunto());
            helper.setText(conteudoHtml, true);
//            helper.setFrom();

            mailSender.send(mimeMessage);
            this.alterarStatusEnvio(emailEnvio);
            System.out.println("✅ Email enviado para: " + pessoa.getNome());
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar email: " + e.getMessage());
        }
    }

    public void alterarStatusEnvio(EmailEnvio emailEnvio){
        emailEnvio.setStatus(true);
        this.salvar(emailEnvio);
    }
}
