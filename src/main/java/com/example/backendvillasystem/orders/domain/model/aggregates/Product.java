package com.example.backendvillasystem.orders.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Double price;

    // Getters y setters
    public Long getId() {  // Método getId
        return id;
    }

    public void setId(Long id) {  // Método setId
        this.id = id;
    }

    // Otros getters y setters para los campos relevantes
}