package com.example.backendvillasystem.auth.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for User Registration
 */
@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String country;
    private String city;
    private String dni;
    private String email;
    private String password;
    private Long roleId; // Cambiado de String a Long para reflejar el ID del rol

    public RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String phone, String address,
                           String country, String city, String dni, String email, String password, Long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.city = city;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.roleId = roleId; // Cambiado para usar el ID del rol
    }
}