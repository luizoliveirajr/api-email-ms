package com.luizmarcelo.apipessoas.service.rabbitmq;

import com.luizmarcelo.apipessoas.config.RabbitMQConfig;
import com.luizmarcelo.apipessoas.model.EmailEnvio;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarEmailParaFila(EmailEnvio emailEnvio) {
        boolean urgencia = emailEnvio.getMensagem().isUrgencia();
        String routingKey = urgencia ? "email.urgente" : "email.normal";
        log.info("📤 Enviando email [{}] para a fila: {}", urgencia ? "URGENTE" : "NORMAL", emailEnvio.getPessoa().getEmail());

        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, routingKey, emailEnvio);
    }
}
