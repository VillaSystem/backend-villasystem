package com.example.backendvillasystem.orders_management.application.internal;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrdersByProducerIdQuery;
import com.example.backendvillasystem.orders_management.domain.services.OrderQueryService;
import com.example.backendvillasystem.orders_management.infrastructure.persistence.jpa.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;

    public OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Handles the GetOrderByIdQuery query.
     *
     * @param query The get order by order number query.
     * @return The order if exists.
     * @throws IllegalArgumentException If orderNumber is null or blank.
     * @see GetOrderByIdQuery
     */
    @Override
    public Optional<Orders> handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.orderId());
    }

    /**
     * Retrieves all order items.
     *
     * @return List of all order items.
     */
    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
