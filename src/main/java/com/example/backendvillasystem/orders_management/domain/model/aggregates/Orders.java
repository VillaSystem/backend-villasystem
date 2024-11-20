package com.example.backendvillasystem.orders_management.domain.model.aggregates;

import com.example.backendvillasystem.orders_management.domain.model.commands.CreateOrderCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Orders Aggregate Root
 *
 * @summary
 * The Orders class is an aggregate root that represents orders in the system.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Orders extends AbstractAggregateRoot<Orders> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber; // renamed from 'numeroPedido'

    @Column(nullable = false)
    private String product; // renamed from 'productos'

    @Column(nullable = false)
    private String transportationCondition; // renamed from 'condicionTransporte'

    @Column(nullable = false)
    private String paymentMethod; // renamed from 'metodoPago'

    @Column(nullable = false)
    private String paymentTerms; // renamed from 'terminosPago'

    @Column(nullable = false)
    private String date; // renamed from 'fecha'

    @Column(nullable = false)
    private String deliveryDate; // renamed from 'fechaEntrega'

    @Column(nullable = false)
    private String type; // renamed from 'tipo'

    @Column(nullable = false)
    private String status; // renamed from 'estado'


    protected Orders() {}

    // Constructor for creating new orders in the system
    public Orders(CreateOrderCommand command) {
        this.orderNumber = command.orderNumber();
        this.product = command.product();
        this.transportationCondition = command.transportationCondition();
        this.paymentMethod = command.paymentMethod();
        this.paymentTerms = command.paymentTerms();
        this.date = command.date();
        this.deliveryDate = command.deliveryDate();
        this.type = command.type();
        this.status = command.status();
    }

    public Orders updateOrder(String orderNumber, String product, String transportationCondition, String paymentMethod,
                              String paymentTerms, String date, String deliveryDate, String type, String status) {
        this.orderNumber = orderNumber;
        this.product = product;
        this.transportationCondition = transportationCondition;
        this.paymentMethod = paymentMethod;
        this.paymentTerms = paymentTerms;
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.type = type;
        this.status = status;
        return this;
    }
}
