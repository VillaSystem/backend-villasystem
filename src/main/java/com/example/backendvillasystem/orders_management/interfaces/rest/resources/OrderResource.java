package com.example.backendvillasystem.orders_management.interfaces.rest.resources;

public record OrderResource(
        Long id,
        String orderNumber,
        String product,
        String transportationCondition,
        String paymentMethod,
        String paymentTerms,
        String date,
        String deliveryDate,
        String type,
        String status
) {

}
