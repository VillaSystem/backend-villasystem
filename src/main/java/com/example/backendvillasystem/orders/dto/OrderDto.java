package com.example.backendvillasystem.orders.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderDto {

    private Long id;
    private String numeroPedido;
    private String condicionTransporte;
    private String metodoPago;
    private String telefono;
    private String telefonoProductor;
    private String terminosPago;
    private LocalDate fecha;
    private LocalDate fechaEntrega;
    private String tipo;
    private String estado;

    // Solo los IDs del producto y consumidor
    private Long productId;
    private Long consumerId;

    // Constructor, getters y setters si es necesario (aunque @Data de Lombok los genera automáticamente)
}