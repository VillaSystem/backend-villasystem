package com.example.backendvillasystem.orders.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
    private List<Order> orders; // Relación con la entidad Order
}