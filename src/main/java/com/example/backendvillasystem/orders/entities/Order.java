package com.example.backendvillasystem.orders.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "orders_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Relación con Product (muchos a uno, ya que un pedido tiene un solo producto)
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Relación con Consumer (muchos a uno, ya que un consumidor puede tener varios pedidos)
    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;
}