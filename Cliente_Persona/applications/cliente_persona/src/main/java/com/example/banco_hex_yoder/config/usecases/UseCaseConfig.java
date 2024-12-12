package com.example.banco_hex_yoder.config.usecases;

import com.example.banco_hex_yoder.service.ClienteService;
import com.example.banco_hex_yoder.usecases.cases.ActualizarClienteUseCase;
import com.example.banco_hex_yoder.usecases.cases.CrearClienteUseCase;
import com.example.banco_hex_yoder.usecases.cases.ConsultarClienteUseCase;
import com.example.banco_hex_yoder.usecases.cases.EliminarClienteUseCase;
import com.example.banco_hex_yoder.usecases.gateway.ClienteRepository;
import com.example.banco_hex_yoder.usecases.gateway.MensajePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class UseCaseConfig {

    @Bean
    public CrearClienteUseCase crearClienteUseCase(ClienteRepository clienteRepository, MensajePublisher mensajePublisher) {
        return new CrearClienteUseCase(clienteRepository, mensajePublisher);
    }

    @Bean
    public ConsultarClienteUseCase consultarClienteUseCase(ClienteRepository clienteRepository) {
        return new ConsultarClienteUseCase(clienteRepository);
    }

    @Bean
    public ActualizarClienteUseCase actualizarClienteUseCase(ClienteRepository clienteRepository, MensajePublisher mensajePublisher) {
        return new ActualizarClienteUseCase(clienteRepository,mensajePublisher);
    }

    @Bean
    public EliminarClienteUseCase eliminarClienteUseCase(ClienteRepository clienteRepository) {
        return new EliminarClienteUseCase(clienteRepository);
    }

    @Bean
    public ClienteService clienteService(
            CrearClienteUseCase crearClienteUseCase,
            ConsultarClienteUseCase consultarClienteUseCase,
            ActualizarClienteUseCase actualizarClienteUseCase,
            EliminarClienteUseCase eliminarClienteUseCase
    ) {
        return new ClienteService(crearClienteUseCase, consultarClienteUseCase, actualizarClienteUseCase, eliminarClienteUseCase);
    }
}
