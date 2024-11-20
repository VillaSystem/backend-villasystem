package com.example.backendvillasystem.orders_management.domain.services;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrdersByProducerIdQuery; // Ensure this query exists

import java.util.List;
import java.util.Optional;

/**
 * @name OrderQueryService
 * @summary
 * This interface represents the service that handles the order queries
 */
public interface OrderQueryService {

    /**
     * Handle the get order by id query
     * @param query The get order by id query
     * @return The order item
     *
     * @throws IllegalArgumentException If order number is null or empty
     * @see GetOrderByIdQuery
     */
    Optional<Orders> handle(GetOrderByIdQuery query);

    /**
     * Retrieve all orders
     * @return List of all orders
     */
    List<Orders> getAllOrders();
}
