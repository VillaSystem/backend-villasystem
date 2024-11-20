package com.example.backendvillasystem.orders_management.domain.model.commands;

public record CreateOrderCommand(
        String orderNumber, // renamed from 'numeroPedido'
        String product, // renamed from 'productos'
        String transportationCondition, // renamed from 'condicionTransporte'
        String paymentMethod, // renamed from 'metodoPago'
        String paymentTerms, // renamed from 'terminosPago'
        String date, // renamed from 'fecha'
        String deliveryDate, // renamed from 'fechaEntrega'
        String type, // renamed from 'tipo'
        String status // renamed from 'estado'
) {
    public CreateOrderCommand {
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
