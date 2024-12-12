package com.example.banco_hex_yoder.usecases.cases;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.usecases.gateway.ClienteRepository;
import com.example.banco_hex_yoder.usecases.gateway.MensajePublisher;

public class CrearClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final MensajePublisher mensajePublisher;

    public CrearClienteUseCase(ClienteRepository clienteRepository, MensajePublisher mensajePublisher) {
        this.clienteRepository = clienteRepository;
        this.mensajePublisher = mensajePublisher;
    }

    public Cliente crearCliente(Cliente cliente) {

        Cliente clienteGuardado = clienteRepository.save(cliente);


        mensajePublisher.publishClienteCreado(clienteGuardado);

        return clienteGuardado;
    }
}
