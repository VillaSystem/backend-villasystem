package com.example.backendvillasystem.client_management.applicaction.internal;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;
import com.example.backendvillasystem.client_management.domain.services.ClientCommandService;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {
    private final ClientRepository clientRepository;

    public ClientCommandServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Handle the create client command
     *
     * @param command The create client command
     * @return The created client
     * @throws IllegalArgumentException If the client already exists
     * @see CreateClientCommand
     */
    @Override
    public Optional<Clients> handle(CreateClientCommand command) {
        if (clientRepository.existsByEmailOrDni(command.email(), command.dni())) {
            throw new IllegalArgumentException("Client with this email or DNI already exists");
        }

        var clients = new Clients(command);
        var createdClient = clientRepository.save(clients);
        return Optional.of(createdClient);
    }

}