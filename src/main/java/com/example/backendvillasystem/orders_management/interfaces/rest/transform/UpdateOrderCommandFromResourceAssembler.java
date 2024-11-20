package com.example.backendvillasystem.orders_management.interfaces.rest.transform;

import com.example.backendvillasystem.orders_management.domain.model.commands.UpdateOrderCommand;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.UpdateOrderResource;

public class UpdateOrderCommandFromResourceAssembler {
    public static UpdateOrderCommand toCommandFromResource(Long Id, UpdateOrderResource resource) {
        return new UpdateOrderCommand(
                Id,
                resource.orderNumber(),
                resource.product(),
                resource.transportationCondition(),
                resource.paymentMethod(),
                resource.paymentTerms(),
                resource.date(),
                resource.deliveryDate(),
                resource.type(),
                resource.status()
        );
    }
}
