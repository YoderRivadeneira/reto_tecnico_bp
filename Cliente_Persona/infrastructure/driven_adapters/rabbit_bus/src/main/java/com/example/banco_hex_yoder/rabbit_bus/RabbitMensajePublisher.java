package com.example.banco_hex_yoder.rabbit_bus;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.usecases.gateway.MensajePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMensajePublisher implements MensajePublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMensajePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishClienteCreado(Cliente cliente) {
        rabbitTemplate.convertAndSend("cliente_exchange", "cliente.creado", cliente);
    }

    @Override
    public void publishClienteActualizado(Cliente cliente) {
        rabbitTemplate.convertAndSend("cliente_exchange", "cliente.actualizado", cliente);
    }
}
