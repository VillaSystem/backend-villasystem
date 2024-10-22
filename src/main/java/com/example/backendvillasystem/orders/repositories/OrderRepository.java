package com.example.backendvillasystem.orders.repositories;

import com.example.backendvillasystem.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEstado(String estado);  // Ejemplo de consulta por estado de orden
}