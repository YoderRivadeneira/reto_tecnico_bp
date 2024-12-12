package com.banco.cuenta_movimientos.model;


import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cuenta_id", nullable = false)
    private Integer cuentaId;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "tipo_movimiento", nullable = false, length = 50)
    private String tipoMovimiento;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "saldo", nullable = false)
    private Double saldo;


    public Movimiento() {
    }


    public Movimiento(Integer id, Integer cuentaId, LocalDateTime fecha, String tipoMovimiento, Double valor, Double saldo) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }



}
