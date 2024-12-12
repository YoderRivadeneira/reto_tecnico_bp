package com.banco.cuenta_movimientos.controller;

import com.banco.cuenta_movimientos.model.Cuenta;
import com.banco.cuenta_movimientos.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Integer id) {
        return ResponseEntity.of(cuentaService.obtenerCuentaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodasLasCuentas() {
        return ResponseEntity.ok(cuentaService.obtenerTodasLasCuentas());
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<Cuenta>> generarReporteEstadoCuenta(
            @RequestParam Integer clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return ResponseEntity.ok(cuentaService.generarReporteEstadoCuenta(clienteId, fechaInicio, fechaFin));
    }


    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crearCuentacontroller(cuenta);
        return ResponseEntity.ok(nuevaCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Integer id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, cuenta);
        return ResponseEntity.ok(cuentaActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Integer id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

}
