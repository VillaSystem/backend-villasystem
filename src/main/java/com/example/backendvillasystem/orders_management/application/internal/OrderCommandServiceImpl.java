package com.example.backendvillasystem.orders_management.application.internal;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import com.example.backendvillasystem.orders_management.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.DeleteOrderCommand;
import com.example.backendvillasystem.orders_management.domain.model.commands.UpdateOrderCommand;
import com.example.backendvillasystem.orders_management.domain.services.OrderCommandService;
import com.example.backendvillasystem.orders_management.infrastructure.persistence.jpa.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;

    public OrderCommandServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Handle the create order command
     *
     * @param command The create order command
     * @return The created order item
     * @throws IllegalArgumentException If the order item already exists
     * @see CreateOrderCommand
     */
    @Override
    public Optional<Orders> handle(CreateOrderCommand command) {
        var orderItem = new Orders(command);
        var createdOrderItem = orderRepository.save(orderItem);
        return Optional.of(createdOrderItem);
    }

    /**
     * Handle the update order command
     *
     * @param command The update order command
     * @return The updated order item
     * @throws IllegalArgumentException If the order item does not exist
     * @see UpdateOrderCommand
     */
    @Override
    public Optional<Orders> handle(UpdateOrderCommand command) {
        var result = orderRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Order item with order number %s not found".formatted(command.orderNumber()));
        }

        var orderToUpdate = result.get();
        try {
            var updatedOrder = orderRepository.save(orderToUpdate.updateOrder(
                    command.orderNumber(),command.product(), command.transportationCondition(), command.paymentMethod(), command.paymentTerms(),
                    command.date(), command.deliveryDate(), command.type(), command.status()));
            return Optional.of(updatedOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating order item: %s".formatted(e.getMessage()));
        }
    }

    /**
     * Handle the delete order command
     *
     * @param command The delete order command
     * @throws IllegalArgumentException If the order item does not exist
     * @see DeleteOrderCommand
     */
    @Override
    public void handle(DeleteOrderCommand command) {
        if (!orderRepository.existsById(command.orderId())) {
            throw new IllegalArgumentException("Order item with order number %s not found".formatted(command.orderId()));
        }
        try {
            orderRepository.deleteById(command.orderId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting order item: %s".formatted(e.getMessage()));
        }
    }
}
