package com.example.backendvillasystem.orders.interfaces.rest.transform;

import com.example.backendvillasystem.orders.domain.model.commands.CreateOrderCommand;
import com.example.backendvillasystem.orders.interfaces.rest.resources.CreateOrderResource;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderCommandFromResourceAssembler {

    public CreateOrderCommand toCommand(CreateOrderResource resource) {
        return new CreateOrderCommand(
                resource.getProductId(),
                resource.getQuantity(),
                resource.getPrice(),
                resource.getNumeroPedido(),
                resource.getFecha().toString(),
                resource.getCondicionTransporte(),
                resource.getMetodoPago(),
                resource.getTelefono(),
                resource.getTelefonoProductor(),
                resource.getTerminosPago(),
                resource.getFecha(),
                resource.getFechaEntrega(),
                resource.getTipo()
        );
    }
}
