package com.example.backendvillasystem.orders.infrastructure.persistence.jpa;

import com.example.backendvillasystem.orders.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Método para encontrar órdenes por estado
    List<Order> findByStatus(String status);
}
