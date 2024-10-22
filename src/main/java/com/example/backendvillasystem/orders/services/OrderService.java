package com.example.backendvillasystem.orders.services;
import com.example.backendvillasystem.orders.entities.Order;
import com.example.backendvillasystem.orders.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional // Agrega esta anotación para asegurar que el método se ejecute dentro de una transacción
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByEstado(String estado) {
        return orderRepository.findByEstado(estado);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}