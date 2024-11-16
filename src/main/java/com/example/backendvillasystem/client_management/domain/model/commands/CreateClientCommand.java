package com.example.backendvillasystem.client_management.domain.model.commands;

/**
 * Command for creating a new client
 */
public class CreateClientCommand {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;
    private final String country;
    private final String city;
    private final String dni;
    private final String email;
    private final String password;
    private final Long roleId; // Campo roleId añadido

    /**
     * Constructor for CreateClientCommand
     *
     * @param firstName The first name of the client
     * @param lastName  The last name of the client
     * @param phone     The phone number of the client
     * @param address   The address of the client
     * @param country   The country of the client
     * @param city      The city of the client
     * @param dni       The DNI (unique identifier) of the client
     * @param email     The email address of the client
     * @param password  The password of the client
     * @param roleId    The role ID of the client (1 = Producer, 2 = Consumer)
     */
    public CreateClientCommand(String firstName, String lastName, String phone, String address,
                               String country, String city, String dni, String email, String password, Long roleId) {
        if (roleId == null || (roleId != 1L && roleId != 2L)) {
            throw new IllegalArgumentException("Role ID must be 1 (Producer) or 2 (Consumer)");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.city = city;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.roleId = roleId; // Inicializar roleId
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getRoleId() {
        return roleId;
    }
}
