package com.example.banco_hex_yoder.model;

public class Cliente {
    private Integer id;
    private Persona persona;
    private String clienteId;
    private String contrasena;
    private Boolean estado;


    public Cliente() {
    }


    public Cliente(Integer id, Persona persona, String clienteId, String contrasena, Boolean estado) {
        this.id = id;
        this.persona = persona;
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
