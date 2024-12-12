package com.example.banco_hex_yoder.usecases.cases;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.usecases.gateway.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ConsultarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ConsultarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> consultarClientePorId(Integer id) {

        return clienteRepository.findById(id);
    }

    public List<Cliente> consultarTodosLosClientes() {
        return clienteRepository.findAll();
    }
}
