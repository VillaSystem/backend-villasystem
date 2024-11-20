package com.example.backendvillasystem.orders_management.domain.services;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import com.example.backendvillasystem.orders_management.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.DeleteOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.UpdateOrderCommand;

import java.util.Optional;

/**
 * @name OrderCommandService
 * @summary
 * This interface represents the service that handles the order commands
 */
public interface OrderCommandService {

    /**
     * Handle the create order command
     * @param command The create order command
     * @return The created order
     *
     * @throws IllegalArgumentException If order data is invalid
     * @see CreateOrderCommand
     */
    Optional<Orders> handle(CreateOrderCommand command);

    /**
     * Handle the update order command
     * @param command The update order command
     * @return The updated order
     *
     * @throws IllegalArgumentException If order data is invalid
     * @see UpdateOrderCommand
     */
    Optional<Orders> handle(UpdateOrderCommand command);

    /**
     * Handle the delete order command
     * @param command The delete order command
     * @throws IllegalArgumentException If order data is invalid
     * @see DeleteOrderCommand
     */
    void handle(DeleteOrderCommand command);
}
