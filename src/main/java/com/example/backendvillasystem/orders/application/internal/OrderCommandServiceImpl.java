package com.example.backendvillasystem.orders.application.internal;

import com.example.backendvillasystem.client.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client.infrastructure.persistence.jpa.ClientRepository;
import com.example.backendvillasystem.orders.domain.services.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.backendvillasystem.orders.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders.domain.model.commands.UpdateOrderCommand;
import com.example.backendvillasystem.orders.infrastructure.persistence.jpa.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Método para crear una nueva orden
    @Override
    public Order createOrder(CreateOrderCommand command) {
        Clients consumer = clientRepository.findById(command.getConsumerId())
                .orElseThrow(() -> new RuntimeException("Consumer not found"));
        Clients producer = clientRepository.findById(command.getProducerId())
                .orElseThrow(() -> new RuntimeException("Producer not found"));

        Order order = new Order(command, consumer, producer);
        return orderRepository.save(order);
    }

    // Método para actualizar el estado de una orden (falta implementar)
    @Override
    public Order updateOrderStatus(UpdateOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Actualiza el estado de la orden
        order.updateStatus(command.getNewStatus());
        return orderRepository.save(order);
    }

    // Método para eliminar una orden
    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}