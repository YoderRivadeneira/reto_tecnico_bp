package com.example.banco_hex_yoder.usecases.cases;

import com.example.banco_hex_yoder.usecases.gateway.ClienteRepository;

public class EliminarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public EliminarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void eliminarCliente(Integer id) {

        clienteRepository.delete(id);
    }
}
