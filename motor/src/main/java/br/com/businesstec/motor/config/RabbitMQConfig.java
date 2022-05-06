package br.com.businesstec.motor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String ROUTING_RM = "serviceRM";
    private static final String ROUTING_JET = "serviceJET";

    @Bean
    Queue queueRm() {
        return new Queue("queue.Rm", false);
    }

    @Bean
    Queue queueJet() {
        return new Queue("queue.Jet", false);
    }

    @Bean
    DirectExchange directExchange(Queue queueRm) {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Binding bindingRm(Queue queueRm, DirectExchange exchange) {
        return BindingBuilder.bind(queueRm).to(exchange).with(ROUTING_RM);
    }

    @Bean
    Binding bindingJet(Queue queueJet, DirectExchange exchange) {
        return BindingBuilder.bind(queueJet).to(exchange).with(ROUTING_JET);
    }

    @Bean
    MessageConverter messageConverter() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;

    }

}
