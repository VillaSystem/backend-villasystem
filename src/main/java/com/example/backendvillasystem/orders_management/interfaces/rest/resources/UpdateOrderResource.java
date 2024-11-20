package com.example.backendvillasystem.orders_management.interfaces.rest.resources;

public record UpdateOrderResource(
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
    public UpdateOrderResource {
        if (orderNumber == null || orderNumber.isBlank()) {
            throw new IllegalArgumentException("Order number cannot be null or empty");
        }
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        if (transportationCondition == null || transportationCondition.isBlank()) {
            throw new IllegalArgumentException("Transportation condition cannot be null or empty");
        }
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        if (paymentTerms == null || paymentTerms.isBlank()) {
            throw new IllegalArgumentException("Payment terms cannot be null or empty");
        }
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (deliveryDate == null || deliveryDate.isBlank()) {
            throw new IllegalArgumentException("Delivery date cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
