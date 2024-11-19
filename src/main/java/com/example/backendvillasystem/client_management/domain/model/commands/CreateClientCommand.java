package com.example.backendvillasystem.client_management.domain.model.commands;

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
    private final String roleName; // Cambiado de Long a String

    public CreateClientCommand(String firstName, String lastName, String phone, String address,
                               String country, String city, String dni, String email, String password, String roleName) {
        if (roleName == null || (!roleName.equalsIgnoreCase("PRODUCER") && !roleName.equalsIgnoreCase("CONSUMER"))) {
            throw new IllegalArgumentException("Role name must be PRODUCER or CONSUMER");
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
        this.roleName = roleName.toUpperCase(); // Asegúrate de que siempre sea mayúsculas
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

    public String getRoleName() {
        return roleName;
    }
}
