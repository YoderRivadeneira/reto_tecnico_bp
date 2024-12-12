package com.example.banco_hex_yoder.postgresql_repository.adapter;

import com.example.banco_hex_yoder.model.Cliente;
import com.example.banco_hex_yoder.model.Persona;
import com.example.banco_hex_yoder.postgresql_repository.entity.ClienteEntity;
import com.example.banco_hex_yoder.postgresql_repository.entity.PersonaEntity;
import com.example.banco_hex_yoder.postgresql_repository.repository.ClienteJpaRepository;
import com.example.banco_hex_yoder.usecases.gateway.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Component
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapToEntity(cliente);
        try {
            System.out.println("ClienteEntity a guardar: " + entity);
            return mapToModel(clienteJpaRepository.save(entity));
        } catch (Exception e) {
            System.err.println("Error al guardar ClienteEntity: " + e.getMessage());
            throw new RuntimeException("Error al guardar el cliente en la base de datos.", e);
        }
    }

    @Override
    public void delete(Integer id) {
        clienteJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteJpaRepository.findById(id).map(this::mapToModel);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }
    private ClienteEntity mapToEntity(Cliente cliente) {
        Persona persona = cliente.getPersona();


        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setId(persona.getId());
        personaEntity.setNombre(persona.getNombre());
        personaEntity.setGenero(persona.getGenero());
        personaEntity.setEdad(persona.getEdad());
        personaEntity.setIdentificacion(persona.getIdentificacion());
        personaEntity.setDireccion(persona.getDireccion());
        personaEntity.setTelefono(persona.getTelefono());


        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setPersona(personaEntity);
        entity.setClienteId(cliente.getClienteId());
        entity.setEstado(cliente.getEstado());
        entity.setContrasena(cliente.getContrasena());
        return entity;
    }


    private String generateUniqueClienteId() {
        return "CLI-" + System.currentTimeMillis();
    }
    private Cliente mapToModel(ClienteEntity entity) {
        PersonaEntity personaEntity = entity.getPersona();


        Persona persona = new Persona(
                personaEntity.getId(),
                personaEntity.getNombre(),
                personaEntity.getGenero(),
                personaEntity.getEdad(),
                personaEntity.getIdentificacion(),
                personaEntity.getDireccion(),
                personaEntity.getTelefono()
        );


        return new Cliente(
                entity.getId(),
                persona,
                entity.getClienteId(),
                entity.getContrasena(),
                entity.getEstado()
        );
    }

    @Override
    public Cliente update(Cliente cliente) {
        ClienteEntity entity = mapToEntity(cliente);
        ClienteEntity updatedEntity = clienteJpaRepository.save(entity);
        return mapToModel(updatedEntity);
    }
}
