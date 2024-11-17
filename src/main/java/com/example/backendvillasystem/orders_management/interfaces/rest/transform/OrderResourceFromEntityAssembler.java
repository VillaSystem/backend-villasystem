package com.example.backendvillasystem.orders_management.interfaces.rest.transform;

import com.example.backendvillasystem.orders_management.domain.model.aggregates.Order;
import com.example.backendvillasystem.orders_management.interfaces.rest.dto.OrderDto;

public class OrderResourceFromEntityAssembler {

    public OrderDto toResource(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setNumeroPedido(String.valueOf(order.getNumeroPedido()));
        dto.setCondicionTransporte(order.getCondicionTransporte());
        dto.setMetodoPago(order.getMetodoPago());
        dto.setTelefono(order.getTelefono());
        dto.setTelefonoProductor(order.getTelefonoProductor());
        dto.setTerminosPago(order.getTerminosPago());
        dto.setFecha(order.getFecha());
        dto.setFechaEntrega(order.getFechaEntrega());
        dto.setTipo(order.getTipo());
        dto.setEstado(order.getEstado());

        //IDs de consumer y producer
        dto.setConsumerId(order.getConsumer().getId());
        dto.setProducerId(order.getProducer().getId());

        return dto;
    }
}