package com.luizmarcelo.apipessoas.service.rabbitmq;

import com.luizmarcelo.apipessoas.config.RabbitMQConfig;
import com.luizmarcelo.apipessoas.model.EmailEnvio;
import com.luizmarcelo.apipessoas.model.Mensagem;
import com.luizmarcelo.apipessoas.model.Pessoa;
import com.luizmarcelo.apipessoas.service.EmailService;
import com.luizmarcelo.apipessoas.service.EmailTemplateService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_URGENTE_QUEUE)
    public void processarEmailUrgente(EmailEnvio emailEnvio) {
        log.info("🔥 Processando EMAIL URGENTE para: {}", emailEnvio.getPessoa().getEmail());
        this.emailService.enviarEmail(emailEnvio);
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_NORMAL_QUEUE)
    public void processarEmailNormal(EmailEnvio emailEnvio) {
        log.info("📩 Processando email NORMAL para: {}", emailEnvio.getPessoa().getEmail());
        this.emailService.enviarEmail(emailEnvio);
    }

}
