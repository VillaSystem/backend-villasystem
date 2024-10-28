package com.example.backendvillasystem.orders.domain.model.aggregates;

import com.example.backendvillasystem.client.domain.model.aggregates.Clients;
import com.example.backendvillasystem.orders.domain.model.commands.CreateOrderCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long numeroPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    private Clients consumer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id", nullable = false)
    private Clients producer;

    @Column(nullable = false)
    private String orderDate;

    // Additional fields
    private String condicionTransporte;
    private String metodoPago;
    private String telefono;
    private String telefonoProductor;
    private String terminosPago;
    private LocalDate fecha;
    private LocalDate fechaEntrega;
    private String tipo;

    protected Order() {}

    public Order(CreateOrderCommand command, Clients consumer, Clients producer) {
        this.productId = command.getProductId();
        this.quantity = command.getQuantity();
        this.price = command.getPrice();
        this.status = "pendiente";  // Initial status
        this.numeroPedido = command.getNumeroPedido();
        this.consumer = consumer;
        this.producer = producer;
        this.orderDate = command.getOrderDate();
        this.condicionTransporte = command.getCondicionTransporte();
        this.metodoPago = command.getMetodoPago();
        this.telefono = command.getTelefono();
        this.telefonoProductor = command.getTelefonoProductor();
        this.terminosPago = command.getTerminosPago();
        this.fecha = command.getFecha();
        this.fechaEntrega = command.getFechaEntrega();
        this.tipo = command.getTipo();
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getEstado() {
        return status;
    }
}
