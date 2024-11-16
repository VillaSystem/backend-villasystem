package com.example.backendvillasystem.client_management.interfaces.rest.resources;

public record CreateClientResource(
        String firstName,
        String lastName,
        String phone,
        String address,
        String country,
        String city,
        String dni,
        String email,
        String password,
        Long roleId // Cambiar el campo role a roleId de tipo Long
) {
    public CreateClientResource {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("DNI cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (roleId == null || (roleId != 1 && roleId != 2)) {
            throw new IllegalArgumentException("Role must be either 1 (Producer) or 2 (Consumer)");
        }
    }
}