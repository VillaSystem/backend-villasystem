package com.example.backendvillasystem.client.applicaction.internal;

import com.example.backendvillasystem.client.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client.domain.model.queries.GetClientsByIdQuery;
import com.example.backendvillasystem.client.domain.services.ClientQueryService;
import com.example.backendvillasystem.client.infrastructure.persistence.jpa.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {
    private final ClientRepository clientRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Clients> handle(GetClientsByIdQuery query) {
        return clientRepository.findById(query.id());
    }
}