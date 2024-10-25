package com.example.backendvillasystem.orders.domain.model.queries;

public class GetOrdersByEstadoQuery {
    private String estado;

    public GetOrdersByEstadoQuery(String estado) {
        this.estado = estado;
    }

    // Getter y setter
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}