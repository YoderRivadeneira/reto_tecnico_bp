package com.banco.cuenta_movimientos.service;

import com.banco.cuenta_movimientos.model.Cuenta;
import com.banco.cuenta_movimientos.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public void actualizarSaldoCuentasPorCliente(Integer clienteId) {
        List<Cuenta> cuentas = cuentaRepository.findAllByClienteId(clienteId);

        if (cuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas para el cliente con ID: " + clienteId);
            return;
        }

        cuentas.forEach(cuenta -> cuenta.setSaldoInicial(0.0));
        cuentaRepository.saveAll(cuentas);

        System.out.println("Saldo de todas las cuentas del cliente con ID: " + clienteId + " ha sido actualizado a 0.");
    }

    public void crearCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    public Cuenta crearCuentacontroller(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
    public void eliminarCuenta(Integer id) {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cuenta no encontrada con ID: " + id);
        }
    }
    public Cuenta actualizarCuenta(Integer id, Cuenta cuentaActualizada) {
        return cuentaRepository.findById(id)
                .map(cuenta -> {
                    cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
                    cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
                    cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
                    cuenta.setEstado(cuentaActualizada.getEstado());
                    cuenta.setClienteId(cuentaActualizada.getClienteId());
                    return cuentaRepository.save(cuenta);
                })
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + id));
    }
    public void actualizarClienteCuenta(Integer clienteId) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findByClienteId(clienteId);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();

            cuentaRepository.save(cuenta);
        } else {
            System.out.println("No se encontró una cuenta para el cliente ID: " + clienteId);
        }
    }

    public Optional<Cuenta> obtenerCuentaPorId(Integer id) {
        return cuentaRepository.findById(id);
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }

    public List<Cuenta> generarReporteEstadoCuenta(Integer clienteId, String fechaInicio, String fechaFin) {

        return cuentaRepository.findAllByClienteId(clienteId);
    }
}