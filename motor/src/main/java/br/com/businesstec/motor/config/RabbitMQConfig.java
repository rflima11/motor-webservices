package br.com.businesstec.motor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    private static final String ROUTING_RM = "serviceRM";
    private static final String ROUTING_JET = "serviceJET";
    private static final String ROUTING_JET_ENTREGA = "serviceJET.Entrega";
    private static final String ROUTING_RM_ENTREGA = "serviceRM.Entrega";

    private final Map<String,Object> arguments;

    public RabbitMQConfig() {
        arguments = getArguments();
    }

    @Bean
    Queue queueRm() {
        return new Queue("queue.Rm", false, false, false, arguments);
    }

    @Bean
    Queue queueJet() {
        return new Queue("queue.Jet", false, false, false, arguments);
    }

    @Bean
    Queue queueJetEntrega() {
        return new Queue("queue.Jet.Entrega", false, false, false, arguments);
    }

    @Bean
    Queue queueRmEntrega() {
        return new Queue("queue.Rm.Entrega", false, false, false, arguments);
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
    Binding bindingRmEntrega(Queue queueRmEntrega, DirectExchange exchange) {
        return BindingBuilder.bind(queueRmEntrega).to(exchange).with(ROUTING_RM_ENTREGA);
    }

    @Bean
    Binding bindingJetEntrega(Queue queueJetEntrega, DirectExchange exchange) {
        return BindingBuilder.bind(queueJetEntrega).to(exchange).with(ROUTING_JET_ENTREGA);
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

    private Map<String, Object> getArguments() {
     Map<String,Object> arguments = new HashMap<String, Object>();
     arguments.put("x-single-active-consumer", true);
     arguments.put("x-message-ttl", 180000);
     return arguments;
    }

}
