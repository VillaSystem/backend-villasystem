package com.example.backendvillasystem.orders.domain.services;

import com.example.backendvillasystem.orders.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders.domain.model.queries.GetOrderByIdQuery;
import com.example.backendvillasystem.orders.domain.model.queries.GetOrdersByEstadoQuery;

import java.util.List;

public interface OrderQueryService {
    List<Order> getAllOrders();
    Order getOrderById(GetOrderByIdQuery query);
    List<Order> getOrdersByEstado(GetOrdersByEstadoQuery query);
}