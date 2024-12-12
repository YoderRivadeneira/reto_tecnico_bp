package com.banco.cuenta_movimientos.controller;

import com.banco.cuenta_movimientos.model.Movimiento;
import com.banco.cuenta_movimientos.model.ReporteEstadoCuenta;
import com.banco.cuenta_movimientos.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }



    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable Integer cuentaId) {
        return ResponseEntity.ok(movimientoService.obtenerMovimientosPorCuenta(cuentaId));
    }



    @PostMapping
    public ResponseEntity<?> registrarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMovimiento(@PathVariable Integer id, @RequestBody Movimiento movimiento) {
        try {
            Movimiento movimientoActualizado = movimientoService.actualizarMovimiento(id, movimiento);
            return ResponseEntity.ok(movimientoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Integer id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/usuario/{clienteId}")
    public ResponseEntity<List<Movimiento>> listarMovimientosPorFechasYUsuario(
            @PathVariable Integer clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        List<Movimiento> movimientos = movimientoService.listarMovimientosPorFechaYUsuario(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }


    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteEstadoCuenta>> generarReporteEstadoCuenta(
            @RequestParam Integer clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        List<ReporteEstadoCuenta> reporte = movimientoService.generarReporteEstadoCuenta(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}
