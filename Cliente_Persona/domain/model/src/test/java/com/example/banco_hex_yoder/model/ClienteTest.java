package com.example.banco_hex_yoder.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ClienteTest {
    @Test
    void testClienteCreation() {
        Persona persona = new Persona(1, "Juan", "M", 30, "12345678", "Calle Verdadera", "0987654321");
        Cliente cliente = new Cliente(1, persona, "uniqueClienteId", "password123", true);
        assertEquals(1, cliente.getId());
        assertEquals("uniqueClienteId", cliente.getClienteId());
        assertEquals("password123", cliente.getContrasena());
        assertTrue(cliente.getEstado());
        assertNotNull(cliente.getPersona());
        assertEquals("Juan", cliente.getPersona().getNombre());
    }
    @Test
    void testSetters() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setClienteId("nuevoId");
        cliente.setContrasena("nuevaPassword");
        cliente.setEstado(false);
        Persona persona = new Persona(2, "Ana", "F", 25, "98765432", "Calle Falsa", "0123456789");
        cliente.setPersona(persona);
        assertEquals(1, cliente.getId());
        assertEquals("nuevoId", cliente.getClienteId());
        assertEquals("nuevaPassword", cliente.getContrasena());
        assertFalse(cliente.getEstado());
        assertNotNull(cliente.getPersona());
        assertEquals("Ana", cliente.getPersona().getNombre());
    }
}