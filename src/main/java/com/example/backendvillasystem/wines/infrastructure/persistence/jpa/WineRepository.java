package com.example.backendvillasystem.wines.infrastructure.persistence.jpa;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for the Wines entity.
 * @summary
 * This interface extends the JpaRepository to provide CRUD operations for the Wines entity.
 * It extends Spring Data JpaRepository with Wines as the entity type
 * and Long as the ID type.
 * @since 1.0
 */
@Repository
public interface WineRepository extends JpaRepository<Wines, Long> {
    List<Wines> findByProducerId(String producerId);
}
