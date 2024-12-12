package com.example.banco_hex_yoder.service;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.usecases.cases.CrearClienteUseCase;
import com.example.banco_hex_yoder.usecases.cases.ConsultarClienteUseCase;
import com.example.banco_hex_yoder.usecases.cases.ActualizarClienteUseCase;
import com.example.banco_hex_yoder.usecases.cases.EliminarClienteUseCase;

import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final CrearClienteUseCase crearClienteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;
    private final ActualizarClienteUseCase actualizarClienteUseCase;
    private final EliminarClienteUseCase eliminarClienteUseCase;

    public ClienteService(
            CrearClienteUseCase crearClienteUseCase,
            ConsultarClienteUseCase consultarClienteUseCase,
            ActualizarClienteUseCase actualizarClienteUseCase,
            EliminarClienteUseCase eliminarClienteUseCase
    ) {
        this.crearClienteUseCase = crearClienteUseCase;
        this.consultarClienteUseCase = consultarClienteUseCase;
        this.actualizarClienteUseCase = actualizarClienteUseCase;
        this.eliminarClienteUseCase = eliminarClienteUseCase;
    }

    public Cliente crearCliente(Cliente cliente) {
        validarCliente(cliente);
        return crearClienteUseCase.crearCliente(cliente);
    }

    public Optional<Cliente> obtenerClientePorId(Integer id) {
        return consultarClienteUseCase.consultarClientePorId(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return consultarClienteUseCase.consultarTodosLosClientes();
    }

    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        validarCliente(cliente);
        return actualizarClienteUseCase.actualizarCliente(id, cliente);
    }

    public void eliminarCliente(Integer id) {
        eliminarClienteUseCase.eliminarCliente(id);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getPersona().getIdentificacion() == null || cliente.getPersona().getIdentificacion().isEmpty()) {
            throw new IllegalArgumentException("La identificación del cliente es obligatoria.");
        }
    }
}
