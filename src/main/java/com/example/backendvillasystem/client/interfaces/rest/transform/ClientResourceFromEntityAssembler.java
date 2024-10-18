package com.example.backendvillasystem.client.interfaces.rest.transform;

import com.example.backendvillasystem.client.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client.interfaces.rest.resources.ClientResource;

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
                entity.getPassword(),
                entity.getRole()

        );
    }
}