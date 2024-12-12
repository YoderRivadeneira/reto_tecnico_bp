package com.example.banco_hex_yoder.config.rabbit;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue clienteCreadoQueue() {
        return new Queue("cliente_creado_queue", true);
    }


    @Bean
    public Queue clienteActualizadoQueue() {
        return new Queue("cliente_actualizado_queue", true);
    }


    @Bean
    public DirectExchange clienteExchange() {
        return new DirectExchange("cliente_exchange");
    }


    @Bean
    public Binding bindingClienteCreado(Queue clienteCreadoQueue, DirectExchange clienteExchange) {
        return BindingBuilder.bind(clienteCreadoQueue).to(clienteExchange).with("cliente.creado");
    }


    @Bean
    public Binding bindingClienteActualizado(Queue clienteActualizadoQueue, DirectExchange clienteExchange) {
        return BindingBuilder.bind(clienteActualizadoQueue).to(clienteExchange).with("cliente.actualizado");
    }


    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}
