package com.example.backendvillasystem.orders_management.infrastructure.persistence.jpa;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for the Orders entity
 * @summary
 * This interface extends the JpaRepository to provide CRUD operations for the Orders entity
 * It extends Spring Data JpaRepository with Orders as the entity type
 * and String as the ID type
 * @since 1.0
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
