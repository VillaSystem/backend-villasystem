package com.example.backendvillasystem.client_management.infrastructure.persistence.jpa;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}