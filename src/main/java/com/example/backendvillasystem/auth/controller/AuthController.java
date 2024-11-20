package com.example.backendvillasystem.auth.controller;

import com.example.backendvillasystem.auth.controller.dto.LoginRequest;
import com.example.backendvillasystem.auth.controller.dto.RegisterRequest;
import com.example.backendvillasystem.auth.domain.model.UserDetailsImpl;
import com.example.backendvillasystem.auth.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Retornar un mapa con los datos del usuario
            return ResponseEntity.ok(Map.of(
                    "id", userDetails.getId(),
                    "firstName", userDetails.getFirstName(),
                    "lastName", userDetails.getLastName(),
                    "phone", userDetails.getPhone(),
                    "address", userDetails.getAddress(),
                    "country", userDetails.getCountry(),
                    "city", userDetails.getCity(),
                    "dni", userDetails.getDni(),
                    "email", userDetails.getUsername(),
                    "role", userDetails.getRoleName()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user is logged in");
    }
}
