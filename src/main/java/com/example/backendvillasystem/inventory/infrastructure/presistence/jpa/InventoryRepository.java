package com.example.backendvillasystem.inventory.infrastructure.presistence.jpa;

import com.example.backendvillasystem.inventory.domain.model.aggregates.Inventories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for the Inventories entity
 * @summary
 * This interface extends the JpaRepository to provide CRUD operations for the Inventories entity
 * It extends Spring Data JpaRepository with Inventories as the entity type
 * and Long as the ID type
 * @since 1.0
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventories, Long> {

    List<Inventories> findByProducerId(Long producerId);
}
