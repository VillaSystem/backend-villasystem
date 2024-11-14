package com.example.backendvillasystem.auth.service;

import com.example.backendvillasystem.auth.controller.dto.JwtResponse;
import com.example.backendvillasystem.auth.controller.dto.LoginRequest;
import com.example.backendvillasystem.auth.controller.dto.RegisterRequest;
import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.ClientRepository;
import com.example.backendvillasystem.auth.domain.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String jwt = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        // Validar si el email o DNI ya existen en la base de datos
        if (clientRepository.existsByEmailOrDni(registerRequest.getEmail(), registerRequest.getDni())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El email o DNI ya están en uso.");
        }

        // Crear el usuario (cliente) a partir de los datos de la solicitud de registro
        Clients newClient = new Clients(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getPhone(),
                registerRequest.getAddress(),
                registerRequest.getCountry(),
                registerRequest.getCity(),
                registerRequest.getDni(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()), // Usar un codificador de contraseñas
                registerRequest.getRole()
        );

        clientRepository.save(newClient);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado exitosamente");
    }
}
