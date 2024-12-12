package com.example.banco_hex_yoder.usecases.gateway;

import com.example.banco_hex_yoder.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Cliente update(Cliente cliente);
    Optional<Cliente> findById(Integer id);
    List<Cliente> findAll();
    void delete(Integer id);
}
