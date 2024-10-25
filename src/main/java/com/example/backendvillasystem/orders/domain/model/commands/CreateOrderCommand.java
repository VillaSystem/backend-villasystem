package com.example.backendvillasystem.orders.domain.model.commands;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateOrderCommand {
    private Long productId;
    private int quantity;
    private double price;
    private Long numeroPedido;
    private String orderDate;
    private String condicionTransporte;
    private String metodoPago;
    private String telefono;
    private String telefonoProductor;
    private String terminosPago;
    private LocalDate fecha;
    private LocalDate fechaEntrega;
    private String tipo;

    public CreateOrderCommand(Long productId, int quantity, double price, Long numeroPedido,
                              String orderDate, String condicionTransporte, String metodoPago,
                              String telefono, String telefonoProductor, String terminosPago,
                              LocalDate fecha, LocalDate fechaEntrega, String tipo) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.numeroPedido = numeroPedido;
        this.orderDate = orderDate;
        this.condicionTransporte = condicionTransporte;
        this.metodoPago = metodoPago;
        this.telefono = telefono;
        this.telefonoProductor = telefonoProductor;
        this.terminosPago = terminosPago;
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.tipo = tipo;
    }
}
