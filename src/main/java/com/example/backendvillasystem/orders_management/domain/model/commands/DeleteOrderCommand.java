package com.example.backendvillasystem.orders_management.domain.model.commands;

public record DeleteOrderCommand(Long orderId) {

    public DeleteOrderCommand {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Order number cannot be null or blank");
        }
    }
}
