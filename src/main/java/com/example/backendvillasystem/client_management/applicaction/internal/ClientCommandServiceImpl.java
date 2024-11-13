package com.example.backendvillasystem.client_management.applicaction.internal;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;
import com.example.backendvillasystem.client_management.domain.model.commands.DeleteClientCommand;
import com.example.backendvillasystem.client_management.domain.model.commands.UpdateClientCommand;
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

    /**
     * Handle the update client command
     *
     * @param command The update client command
     * @return The updated client
     * @throws IllegalArgumentException If ClientApiKey is null or empty
     * @see UpdateClientCommand
     */
    @Override
    public Optional<Clients> handle(UpdateClientCommand command) {
        if (clientRepository.existsByDniAndIdIsNot( command.dni(), command.clientId()))
            throw new IllegalArgumentException("Client with DNI %s already exists".formatted(command.dni()));
        var result = clientRepository.findById(command.clientId());
            if (result.isEmpty())
                throw new IllegalArgumentException("Client with id %s not found".formatted(command.clientId()));
            var clientToUpdate = result.get();
            try {
                var updatedClient = clientRepository.save(clientToUpdate.updateClient(command.firstName(),
                        command.lastName(), command.phone(), command.address(), command.country(), command.city(),
                        command.dni(), command.email(), command.role()));
                return Optional.of(updatedClient);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while updating client: %s".formatted(e.getMessage()));
            }

    }

    /**
     * Handle the delete client command
     *
     * @param command The delete client command
     * @throws IllegalArgumentException If ClientApiKey is null or empty
     * @see DeleteClientCommand
     */
    @Override
    public void handle(DeleteClientCommand command) {
        if (!clientRepository.existsById(command.clientId())) {
            throw new IllegalArgumentException("Client with id %s not found".formatted(command.clientId()));
        }
        try {
            clientRepository.deleteById(command.clientId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting client: %s".formatted(e.getMessage()));
        }

    }

}