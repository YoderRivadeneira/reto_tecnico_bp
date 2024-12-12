package com.example.banco_hex_yoder.controller;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Operaciones relacionadas con el manejo de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Este endpoint permite crear un nuevo cliente dentro del sistema.")
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @Operation(summary = "Obtener un cliente por ID", description = "Obtiene los detalles de un cliente específico proporcionando su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(
            @Parameter(description = "ID del cliente a consultar", example = "1")
            @PathVariable Integer id
    ) {
        return ResponseEntity.of(clienteService.obtenerClientePorId(id));
    }

    @Operation(summary = "Obtener todos los clientes", description = "Este endpoint devuelve una lista con todos los clientes existentes.")
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
    }

    @Operation(summary = "Actualizar un cliente", description = "Permite actualizar los detalles de un cliente existente proporcionando su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @Parameter(description = "ID del cliente a actualizar", example = "1")
            @PathVariable Integer id,
            @RequestBody Cliente cliente
    ) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente existente proporcionando su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @Parameter(description = "ID del cliente a eliminar", example = "1")
            @PathVariable Integer id
    ) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}