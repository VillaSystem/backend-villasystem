package com.example.backendvillasystem.orders.domain.services;

import com.example.backendvillasystem.orders.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders.domain.model.commands.UpdateOrderCommand;

public interface OrderCommandService {
    Order createOrder(CreateOrderCommand command);
    Order updateOrderStatus(UpdateOrderCommand command);
    void deleteOrder(Long orderId);
}