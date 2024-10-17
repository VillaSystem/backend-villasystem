package com.example.backendvillasystem.client.interfaces.rest;

import com.example.backendvillasystem.client.domain.model.aggregates.Clients;

public class ClientResourceFromEntityAssembler {
    public static ClientResource toResourceFromEntity(Clients entity) {
        return new ClientResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCountry(),
                entity.getCity(),
                entity.getDni(),
                entity.getEmail(),
                entity.getPassword()

        );
    }
}