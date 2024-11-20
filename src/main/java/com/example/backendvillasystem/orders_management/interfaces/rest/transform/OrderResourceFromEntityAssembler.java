package com.example.backendvillasystem.orders_management.interfaces.rest.transform;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Orders;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.OrderResource;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Orders entity) {
        return new OrderResource(
                entity.getId(),
                entity.getOrderNumber(),
                entity.getProduct(),
                entity.getTransportationCondition(),
                entity.getPaymentMethod(),
                entity.getPaymentTerms(),
                entity.getDate(),
                entity.getDeliveryDate(),
                entity.getType(),
                entity.getStatus()
        );
    }
}
