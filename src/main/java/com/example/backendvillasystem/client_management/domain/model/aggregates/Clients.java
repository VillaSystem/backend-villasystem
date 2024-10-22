package com.example.backendvillasystem.client_management.domain.model.aggregates;

import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;
import jakarta.persistence.*;
import lombok.Getter;
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
}