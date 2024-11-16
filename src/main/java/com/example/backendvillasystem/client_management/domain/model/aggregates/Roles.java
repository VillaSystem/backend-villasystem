package com.example.backendvillasystem.client_management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Roles Entity
 *
 * @summary
 * Represents roles in the system.
 */
@Entity
@Table(name = "roles") // Nombre explícito de la tabla
@Getter
@Setter
@NoArgsConstructor // Constructor vacío para JPA
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Debe coincidir con el tipo de "role_id" en Clients

    @Column(nullable = false, unique = true)
    private String name;

    // Constructor
    public Roles(String name) {
        this.name = name;
    }
}
