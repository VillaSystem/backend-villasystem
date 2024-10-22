package com.example.backendvillasystem.orders.controllers;

import com.example.backendvillasystem.orders.entities.Order;
import com.example.backendvillasystem.orders.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Crear una nueva orden
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Obtener órdenes por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Order>> getOrdersByEstado(@PathVariable String estado) {
        List<Order> orders = orderService.getOrdersByEstado(estado);
        return ResponseEntity.ok(orders);
    }

    // Eliminar una orden
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}