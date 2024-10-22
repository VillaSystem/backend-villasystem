package com.example.backendvillasystem.client_management.infrastructure.persistence.jpa;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * JPA Repository for the Clients entity
 * @summary
 * This interface extends the JpaRepository to provide CRUD operations for the Clients entity
 * It extends Spring Data JpaRepository with Clients as the entity type
 * and Long as the ID type
 * @since 1.0
 */
@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {
    /**
     * Check if a client exists by email or DNI
     * @param email The email of the client
     * @param dni The DNI of the client
     * @return True if a client with the given email or DNI exists, false otherwise
     */
    boolean existsByEmailOrDni(String email, String dni);

    List<Clients> findByRole(String role);
}