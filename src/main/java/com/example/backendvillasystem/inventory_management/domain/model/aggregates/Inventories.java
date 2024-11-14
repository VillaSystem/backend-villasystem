package com.example.backendvillasystem.inventory_management.domain.model.aggregates;

import com.example.backendvillasystem.inventory_management.domain.model.commands.CreateInventoryCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Inventories Aggregate Root
 *
 * @summary
 * The Inventories class is an aggregate root that represents items in the inventory.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Inventories extends AbstractAggregateRoot<Inventories> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // renamed from 'nombre'

    @Column(nullable = false)
    private String type; // renamed from 'tipo'

    @Column(nullable = false)
    private String unit; // renamed from 'unidad'

    @Column
    private String expirationDate; // renamed from 'caducidad'

    @Column(nullable = false)
    private String supplier; // renamed from 'proveedor'

    @Column(nullable = false)
    private Double unitCost; // renamed from 'costoU'

    @Column(nullable = false)
    private int quantity; // renamed from 'cantidad'

    @Column
    private String lastUpdated; // renamed from 'ultimaActualizacion'

    @Column(nullable = false)
    private Long producerId; // new field for producer ID

    protected Inventories() {}

    // Constructor for creating new items in the inventory
    public Inventories(CreateInventoryCommand command) {
        this.name = command.name();
        this.type = command.type();
        this.unit = command.unit();
        this.expirationDate = command.expirationDate();
        this.supplier = command.supplier();
        this.unitCost = command.unitCost();
        this.quantity = command.quantity();
        this.lastUpdated = command.lastUpdated();
        this.producerId = command.producerId(); // assign producer ID
    }

    public Inventories updateInventory(String name, String type, String unit, String expirationDate,
                                       String supplier, Double unitCost, int quantity,
                                       String lastUpdated, Long producerId) {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.producerId = producerId;
        return this;
    }

}
