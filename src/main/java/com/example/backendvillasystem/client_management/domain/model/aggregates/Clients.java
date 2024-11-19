package com.example.backendvillasystem.client_management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * Clients Aggregate Root
 *
 * Represents clients in the system and handles their role assignment and attributes.
 */
@Entity
@Getter
@Setter
@ToString(exclude = "password") // Excluye la contraseña de los logs por seguridad
public class Clients extends AbstractAggregateRoot<Clients> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // Campo string para almacenar el rol del usuario

    // Constructor vacío requerido por JPA
    protected Clients() {}

    // Constructor completo para registro directo
    public Clients(String firstName, String lastName, String phone, String address,
                   String country, String city, String dni, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.city = city;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
