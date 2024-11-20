package com.example.backendvillasystem.orders_management.interfaces.rest.transform;

import com.example.backendvillasystem.orders_management.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders_management.interfaces.rest.resources.CreateOrderResource;

public class CreateOrderCommandFromResourceAssembler {

    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        return new CreateOrderCommand(
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
