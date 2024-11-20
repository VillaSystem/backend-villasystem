package com.example.backendvillasystem.orders_management.domain.model.commands;

public record UpdateOrderCommand(
        Long id, // Agregado para incluir el identificador único del pedido
        String orderNumber, // Número de orden (no es el ID)
        String product, // Producto
        String transportationCondition, // Condición de transporte
        String paymentMethod, // Método de pago
        String paymentTerms, // Términos de pago
        String date, // Fecha
        String deliveryDate, // Fecha de entrega
        String type, // Tipo
        String status // Estado
) {

    public UpdateOrderCommand {
        // Validaciones
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or less than or equal to 0");
        }
        if (orderNumber == null || orderNumber.isBlank()) {
            throw new IllegalArgumentException("Order number cannot be null or blank");
        }
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Product cannot be null or blank");
        }
        if (transportationCondition == null || transportationCondition.isBlank()) {
            throw new IllegalArgumentException("Transportation condition cannot be null or blank");
        }
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or blank");
        }
        if (paymentTerms == null || paymentTerms.isBlank()) {
            throw new IllegalArgumentException("Payment terms cannot be null or blank");
        }
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be null or blank");
        }
        if (deliveryDate == null || deliveryDate.isBlank()) {
            throw new IllegalArgumentException("Delivery date cannot be null or blank");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or blank");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
    }
}
