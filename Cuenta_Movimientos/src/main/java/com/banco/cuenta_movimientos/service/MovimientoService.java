package com.banco.cuenta_movimientos.service;

import com.banco.cuenta_movimientos.model.Cuenta;
import com.banco.cuenta_movimientos.model.Movimiento;
import com.banco.cuenta_movimientos.model.ReporteEstadoCuenta;
import com.banco.cuenta_movimientos.repository.CuentaRepository;
import com.banco.cuenta_movimientos.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }


    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (movimiento.getValor() < 0 && nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setSaldo(nuevoSaldo);
        return movimientoRepository.save(movimiento);
    }


    public Movimiento actualizarMovimiento(Integer id, Movimiento movimientoActualizado) {
        Movimiento movimientoExistente = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        Cuenta cuenta = cuentaRepository.findById(movimientoExistente.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Double saldoRevertido = cuenta.getSaldoInicial() - movimientoExistente.getValor();

        Double nuevoSaldo = saldoRevertido + movimientoActualizado.getValor();
        if (movimientoActualizado.getValor() < 0 && nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimientoExistente.setTipoMovimiento(movimientoActualizado.getTipoMovimiento());
        movimientoExistente.setValor(movimientoActualizado.getValor());
        movimientoExistente.setSaldo(nuevoSaldo);
        movimientoExistente.setFecha(movimientoActualizado.getFecha());

        return movimientoRepository.save(movimientoExistente);
    }
    public List<ReporteEstadoCuenta> generarReporteEstadoCuenta(Integer clienteId, String fechaInicio, String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);

        List<Cuenta> cuentas = cuentaRepository.findAllByClienteId(clienteId);

        if (cuentas.isEmpty()) {
            throw new RuntimeException("No se encontraron cuentas asociadas al cliente con ID: " + clienteId);
        }

        List<ReporteEstadoCuenta> reportes = cuentas.stream().map(cuenta -> {
            List<Movimiento> movimientos = movimientoRepository.findByFechaBetweenAndCuentaIdIn(inicio, fin, List.of(cuenta.getId()));

            return new ReporteEstadoCuenta(
                    cuenta.getNumeroCuenta(),
                    cuenta.getSaldoInicial(),
                    movimientos,
                    calcularSaldoFinal(movimientos, cuenta.getSaldoInicial())
            );
        }).toList();

        return reportes;
    }

    private Double calcularSaldoFinal(List<Movimiento> movimientos, Double saldoInicial) {
        return movimientos.stream()
                .mapToDouble(Movimiento::getValor)
                .sum() + saldoInicial;
    }

    public List<Movimiento> listarMovimientosPorFechaYUsuario(Integer clienteId, String fechaInicio, String fechaFin) {
        List<Cuenta> cuentas = cuentaRepository.findAllByClienteId(clienteId);

        return movimientoRepository.findAll().stream()
                .filter(movimiento -> cuentas.stream().anyMatch(cuenta -> cuenta.getId().equals(movimiento.getCuentaId())) &&
                        movimiento.getFecha().isAfter(LocalDateTime.parse(fechaInicio)) &&
                        movimiento.getFecha().isBefore(LocalDateTime.parse(fechaFin)))
                .toList();
    }
    public void eliminarMovimiento(Integer id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimiento.getValor());
        cuentaRepository.save(cuenta);

        movimientoRepository.deleteById(id);
    }
    public List<Movimiento> obtenerMovimientosPorCuenta(Integer cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }
}
