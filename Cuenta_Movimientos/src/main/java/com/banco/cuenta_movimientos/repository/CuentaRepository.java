package com.banco.cuenta_movimientos.repository;

import com.banco.cuenta_movimientos.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    Optional<Cuenta> findByClienteId(Integer clienteId);
    List<Cuenta> findAllByClienteId(Integer clienteId);
}
