package com.example.backendvillasystem.orders_management.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Command to update the status of an order
 */
@Getter
@AllArgsConstructor
public class UpdateOrderCommand {
    private Long orderId;
    private String newStatus;
}
