package com.example.banco_hex_yoder.usecases.gateway;

import com.example.banco_hex_yoder.model.Cliente;

public interface MensajePublisher {
    void publishClienteCreado(Cliente cliente);
    void publishClienteActualizado(Cliente cliente);
}
