package com.example.backendvillasystem.client_management.domain.model.aggregates;

import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Clients Aggregate Root
 *
 * @summary
 * The Clients class is an aggregate root that represents new clients.
 * It is responsible for handling the CreateClient command.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter // Añadir Setter para poder modificar datos si es necesario
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
    private String role;

    protected Clients() {}

    // Constructor para manejar el comando de creación
    public Clients(CreateClientCommand command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.phone = command.phone();
        this.address = command.address();
        this.country = command.country();
        this.city = command.city();
        this.dni = command.dni();
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
    }

    // Constructor adicional para el registro
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