package com.example.backendvillasystem.orders_management.domain.model.queries;

public class GetOrderByIdQuery {

    private final Long orderId;

    // Constructor
    public GetOrderByIdQuery(Long orderId) {
        this.orderId = orderId;
    }

    //  getter
    public Long getOrderId() {
        return orderId;
    }
}