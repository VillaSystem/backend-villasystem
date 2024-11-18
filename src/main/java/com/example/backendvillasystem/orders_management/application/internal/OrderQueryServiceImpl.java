package com.example.backendvillasystem.orders_management.application.internal;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrdersByEstadoQuery;
import com.example.backendvillasystem.orders_management.domain.services.OrderQueryService;
import com.example.backendvillasystem.orders_management.infrastructure.persistence.jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(GetOrderByIdQuery query) {
        return orderRepository.findById(query.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByEstado(GetOrdersByEstadoQuery query) {
        return orderRepository.findByStatus(query.getEstado());
    }
}