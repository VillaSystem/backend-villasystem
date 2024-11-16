package com.example.backendvillasystem.client_management.applicaction.internal;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import com.example.backendvillasystem.client_management.domain.model.commands.CreateClientCommand;
import com.example.backendvillasystem.client_management.domain.services.ClientCommandService;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.ClientRepository;
import com.example.backendvillasystem.client_management.infrastructure.persistence.jpa.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;

    public ClientCommandServiceImpl(ClientRepository clientRepository, RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Handle the create client command
     *
     * @param command The create client command
     * @return The created client
     * @throws IllegalArgumentException If the client already exists or the role is invalid
     * @see CreateClientCommand
     */
    @Override
    public Optional<Clients> handle(CreateClientCommand command) {
        // Verificar si el cliente ya existe por email o DNI
        if (clientRepository.existsByEmailOrDni(command.getEmail(), command.getDni())) {
            throw new IllegalArgumentException("Client with this email or DNI already exists");
        }

        // Buscar el rol en la base de datos por ID
        var role = roleRepository.findById(command.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        // Crear el cliente con el rol asignado
        var client = new Clients(
                command.getFirstName(),
                command.getLastName(),
                command.getPhone(),
                command.getAddress(),
                command.getCountry(),
                command.getCity(),
                command.getDni(),
                command.getEmail(),
                command.getPassword(),
                role
        );

        // Guardar el cliente en la base de datos
        var createdClient = clientRepository.save(client);

        return Optional.of(createdClient);
    }
}
