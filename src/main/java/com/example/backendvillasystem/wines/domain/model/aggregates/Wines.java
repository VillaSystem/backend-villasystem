package com.example.backendvillasystem.wines.domain.model.aggregates;

import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
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
}
