package com.banco.cuenta_movimientos.controller;

import com.banco.cuenta_movimientos.Main;
import com.banco.cuenta_movimientos.model.Cuenta;
import com.banco.cuenta_movimientos.model.Movimiento;
import com.banco.cuenta_movimientos.repository.CuentaRepository;
import com.banco.cuenta_movimientos.repository.MovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class MovimientoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @BeforeEach
    void setup() {
        movimientoRepository.deleteAll();
        cuentaRepository.deleteAll();

        Cuenta cuenta = new Cuenta(null, "NC-1234", "Ahorro", 1000.0, true, 1);
        cuentaRepository.save(cuenta);

        Movimiento movimiento1 = new Movimiento(null, cuenta.getId(), LocalDateTime.now().minusDays(1), "Deposito", 500.0, 1500.0);
        Movimiento movimiento2 = new Movimiento(null, cuenta.getId(), LocalDateTime.now().minusDays(2), "Retiro", -200.0, 1300.0);
        movimientoRepository.saveAll(List.of(movimiento1, movimiento2));
    }

    @Test
    void testGenerarReporteEstadoCuenta() throws Exception {

        Integer clienteId = 1;
        String fechaInicio = LocalDateTime.now().minusDays(3).toString();
        String fechaFin = LocalDateTime.now().toString();

        mockMvc.perform(get("/api/movimientos/reporte")
                        .param("clienteId", clienteId.toString())
                        .param("fechaInicio", fechaInicio)
                        .param("fechaFin", fechaFin)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numeroCuenta").value("NC-1234"))
                .andExpect(jsonPath("$[0].movimientos", hasSize(2)))
                .andExpect(jsonPath("$[0].movimientos[0].tipoMovimiento").value("Deposito"))
                .andExpect(jsonPath("$[0].movimientos[1].tipoMovimiento").value("Retiro"));
    }
}