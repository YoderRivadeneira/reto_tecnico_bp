package com.example.banco_hex_yoder.postgresql_repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 1)
    private String genero;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false, unique = true, length = 50)
    private String identificacion;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false, length = 15)
    private String telefono;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {


        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
