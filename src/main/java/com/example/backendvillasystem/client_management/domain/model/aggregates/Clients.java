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
 * Represents clients in the system and handles the CreateClientCommand.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
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

    // Relación con Roles
    @ManyToOne(fetch = FetchType.EAGER) // EAGER para cargar siempre el rol
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Roles role;

    // Constructor vacío requerido por JPA
    protected Clients() {}

    // Constructor para manejar CreateClientCommand
    public Clients(CreateClientCommand command, Roles role) {
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
        this.phone = command.getPhone();
        this.address = command.getAddress();
        this.country = command.getCountry();
        this.city = command.getCity();
        this.dni = command.getDni();
        this.email = command.getEmail();
        this.password = command.getPassword();
        this.role = role;
    }

    // Constructor para registro
    public Clients(String firstName, String lastName, String phone, String address,
                   String country, String city, String dni, String email, String password, Roles role) {
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
