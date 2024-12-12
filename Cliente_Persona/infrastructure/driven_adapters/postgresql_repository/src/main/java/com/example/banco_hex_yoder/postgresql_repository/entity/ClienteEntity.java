package com.example.banco_hex_yoder.postgresql_repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private PersonaEntity persona;

    @Column(nullable = false, unique = true, length = 50)
    private String clienteId;

    @Column(nullable = false, length = 200)
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
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
