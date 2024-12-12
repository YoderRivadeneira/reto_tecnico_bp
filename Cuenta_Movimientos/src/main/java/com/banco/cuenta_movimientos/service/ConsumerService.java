package com.banco.cuenta_movimientos.service;
import com.banco.cuenta_movimientos.model.Cuenta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ConsumerService {

    private final CuentaService cuentaService;

    public ConsumerService(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @RabbitListener(queues = "cliente_creado_queue")
    public void handleClienteCreado(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> clienteData = objectMapper.readValue(payload, Map.class);


            Integer clienteId = (Integer) clienteData.get("id");


            Cuenta nuevaCuenta = new Cuenta();
            nuevaCuenta.setClienteId(clienteId);
            nuevaCuenta.setSaldoInicial(1000.0);
            nuevaCuenta.setEstado(true);


            String numeroCuentaGenerado = "NC-" + clienteId + "-" + System.currentTimeMillis();
            nuevaCuenta.setNumeroCuenta(numeroCuentaGenerado);


            nuevaCuenta.setTipoCuenta("Ahorro");

            cuentaService.crearCuenta(nuevaCuenta);

            System.out.println("Cuenta creada para el cliente ID: " + clienteId);

        } catch (Exception e) {
            System.err.println("Error procesando el mensaje de cliente creado: " + e.getMessage());
        }
    }


    @RabbitListener(queues = "cliente_actualizado_queue")
    public void handleClienteActualizado(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> clienteData = objectMapper.readValue(payload, Map.class);

            Integer clienteId = (Integer) clienteData.get("id");
            Boolean estadoNuevo = (Boolean) clienteData.get("estado");


            if (estadoNuevo != null && !estadoNuevo) {
                cuentaService.actualizarSaldoCuentasPorCliente(clienteId);
                System.out.println("Saldo de todas las cuentas asociado al cliente ID: " + clienteId + " puesto a 0 debido a cambio de estado a false.");
            }

        } catch (Exception e) {
            System.err.println("Error procesando el mensaje de cliente actualizado: " + e.getMessage());
        }
    }
}
