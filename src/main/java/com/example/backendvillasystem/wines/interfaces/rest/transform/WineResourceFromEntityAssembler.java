package com.example.backendvillasystem.wines.interfaces.rest.transform;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.interfaces.rest.resources.WineResource;

public class WineResourceFromEntityAssembler {
    public static WineResource toResourceFromEntity(Wines entity) {
        return new WineResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getRegion(),
                entity.getCountry(),
                entity.getYear(),
                entity.getGrapes(),
                entity.getAlcohol(),
                entity.getCertification(),
                entity.getRating(),
                entity.getState(),
                entity.getProducerId(),
                entity.getBatchId(),
                entity.getLink() // Include link if necessary
        );
    }
}
