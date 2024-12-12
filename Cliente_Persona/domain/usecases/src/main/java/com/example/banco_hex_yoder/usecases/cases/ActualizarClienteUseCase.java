package com.example.banco_hex_yoder.usecases.cases;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.usecases.gateway.ClienteRepository;
import com.example.banco_hex_yoder.usecases.gateway.MensajePublisher;

public class ActualizarClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final MensajePublisher mensajePublisher;

    public ActualizarClienteUseCase(ClienteRepository clienteRepository, MensajePublisher mensajePublisher) {
        this.clienteRepository = clienteRepository;
        this.mensajePublisher = mensajePublisher;
    }

    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));


        clienteExistente.setPersona(cliente.getPersona());
        clienteExistente.setEstado(cliente.getEstado());


        Cliente clienteActualizado = clienteRepository.save(clienteExistente);


        mensajePublisher.publishClienteActualizado(clienteActualizado);

        return clienteActualizado;
    }
}
