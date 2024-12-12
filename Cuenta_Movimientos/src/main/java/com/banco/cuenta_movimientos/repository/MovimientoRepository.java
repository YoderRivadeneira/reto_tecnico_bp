package com.banco.cuenta_movimientos.repository;

import com.banco.cuenta_movimientos.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByCuentaId(Integer cuentaId);


    List<Movimiento> findByFechaBetweenAndCuentaIdIn(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<Integer> cuentaIds);
}
