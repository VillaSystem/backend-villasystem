package com.example.backendvillasystem.wines.domain.model.aggregates;

import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Wines extends AbstractAggregateRoot<Wines> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String producer;

    protected Wines() {}

    public Wines(CreateWineCommand command) {
        this.name = command.name();
        this.year = command.year();
        this.type = command.type();
        this.country = command.country();
        this.producer = command.producer();
    }

    // Método update para modificar los campos de la entidad
    public void update(UpdateWineCommand command) {
        if (command.name() != null && !command.name().isBlank()) this.name = command.name();
        if (command.year() != null && !command.year().isBlank()) this.year = command.year();
        if (command.type() != null && !command.type().isBlank()) this.type = command.type();
        if (command.country() != null && !command.country().isBlank()) this.country = command.country();
        if (command.producer() != null && !command.producer().isBlank()) this.producer = command.producer();
    }
}
