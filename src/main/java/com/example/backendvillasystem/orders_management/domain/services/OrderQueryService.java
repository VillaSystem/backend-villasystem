package com.example.backendvillasystem.orders_management.domain.services;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders_management.domain.model.queries.GetOrdersByEstadoQuery;

import java.util.List;

public interface OrderQueryService {
    List<Order> getAllOrders();
    Order getOrderById(GetOrderByIdQuery query);
    List<Order> getOrdersByEstado(GetOrdersByEstadoQuery query);
}