package com.banco.cuenta_movimientos.model;

import java.util.List;

public class ReporteEstadoCuenta {

    private String numeroCuenta;
    private Double saldoInicial;
    private List<Movimiento> movimientos;
    private Double saldoFinal;

    public ReporteEstadoCuenta(String numeroCuenta, Double saldoInicial, List<Movimiento> movimientos, Double saldoFinal) {
        this.numeroCuenta = numeroCuenta;
        this.saldoInicial = saldoInicial;
        this.movimientos = movimientos;
        this.saldoFinal = saldoFinal;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
}