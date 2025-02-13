package com.luizmarcelo.apipessoas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMAIL_URGENTE_QUEUE = "email_urgente_queue";
    public static final String EMAIL_NORMAL_QUEUE = "email_normal_queue";
    public static final String EMAIL_EXCHANGE = "email_exchange";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

    @Bean
    public Queue urgenteQueue() {
        return new Queue(EMAIL_URGENTE_QUEUE, true);
    }

    @Bean
    public Queue normalQueue() {
        return new Queue(EMAIL_NORMAL_QUEUE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public Binding urgenteBinding(Queue urgenteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(urgenteQueue).to(exchange).with("email.urgente");
    }

    @Bean
    public Binding normalBinding(Queue normalQueue, DirectExchange exchange) {
        return BindingBuilder.bind(normalQueue).to(exchange).with("email.normal");
    }
}
