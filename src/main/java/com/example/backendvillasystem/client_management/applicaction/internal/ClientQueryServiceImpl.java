package com.example.backendvillasystem.client_management.applicaction.internal;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.queries.GetClientsByIdQuery;
import com.example.backendvillasystem.client_management.domain.model.queries.GetClientsByRoleQuery;
import com.example.backendvillasystem.client_management.domain.services.ClientQueryService;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {
    private final ClientRepository clientRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Handles the GetClientsByIdQuery query.
     *
     * @param query The get clients by id query.
     * @return The client if exists.
     * @throws IllegalArgumentException If id is null.
     * @see GetClientsByIdQuery
     */
    @Override
    public Optional<Clients> handle(GetClientsByIdQuery query) {
        return clientRepository.findById(query.id());
    }

    /**
     * Retrieves all clients.
     *
     * @return List of all clients.
     */
    @Override
    public List<Clients> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Handles the GetClientsByRoleQuery query.
     * @param query The get clients by role query.
     * @return List of clients with the given role.
     * @see GetClientsByRoleQuery
     */
    @Override
    public List<Clients> handle(GetClientsByRoleQuery query) {
        return clientRepository.findByRole(query.role());
    }
}