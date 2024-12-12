package com.banco.cuenta_movimientos.configuration;

import org.springframework.amqp.core.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue clienteQueue() {
        return new Queue("cliente_queue", true);
    }

    @Bean
    public DirectExchange clienteExchange() {
        return new DirectExchange("cliente_exchange");
    }

    @Bean
    public Binding bindingCliente(Queue clienteQueue, DirectExchange clienteExchange) {

        return BindingBuilder.bind(clienteQueue)
                .to(clienteExchange)
                .with("cliente.#");
    }
}
