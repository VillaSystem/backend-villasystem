package com.example.backendvillasystem.orders_management.domain.services;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders_management.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.UpdateOrderCommand;

public interface OrderCommandService {
    Order createOrder(CreateOrderCommand command);
    Order updateOrderStatus(UpdateOrderCommand command);
    void deleteOrder(Long orderId);
}