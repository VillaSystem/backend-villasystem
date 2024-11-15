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
    private String role;

    public RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String phone, String address,
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