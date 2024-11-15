package com.example.backendvillasystem.wines.interfaces.rest.transform;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import com.example.backendvillasystem.wines.interfaces.rest.resources.WineResource;

public class WineResourceFromEntityAssembler {
    public static WineResource toResource(Wines wine) {
        return new WineResource(
                wine.getId(),
                wine.getName(),
                wine.getYear(),
                wine.getType(),
                wine.getCountry(),
                wine.getProducer()
        );
    }
}
