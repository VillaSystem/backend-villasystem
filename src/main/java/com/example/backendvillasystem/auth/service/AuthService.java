package com.example.backendvillasystem.auth.service;

import com.example.backendvillasystem.auth.controller.dto.JwtResponse;
import com.example.backendvillasystem.auth.controller.dto.LoginRequest;
import com.example.backendvillasystem.auth.controller.dto.RegisterRequest;
import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.ClientRepository;
import com.example.backendvillasystem.auth.domain.model.UserDetailsImpl;
import com.example.backendvillasystem.client_management.domain.model.aggregates.Roles;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.RoleRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Autenticación del usuario y generación del token con el rol
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // Obtener los detalles del usuario autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Generar el token JWT con el nombre de usuario y rol
        String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getRoleName());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    // Registro del usuario
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        // Validar si el email o DNI ya existen en la base de datos
        if (clientRepository.existsByEmailOrDni(registerRequest.getEmail(), registerRequest.getDni())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El email o DNI ya están en uso.");
        }

        // Buscar el rol en la base de datos por ID
        Roles role = roleRepository.findById(registerRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Error: El rol proporcionado no es válido."));

        // Crear el usuario
        Clients newClient = new Clients(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getPhone(),
                registerRequest.getAddress(),
                registerRequest.getCountry(),
                registerRequest.getCity(),
                registerRequest.getDni(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                role // Se pasa la entidad Roles en lugar de un String
        );

        // Guardar el cliente en la base de datos
        clientRepository.save(newClient);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado exitosamente");
    }
}