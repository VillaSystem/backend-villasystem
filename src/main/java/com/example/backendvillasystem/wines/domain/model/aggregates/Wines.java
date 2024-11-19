package com.example.backendvillasystem.wines.domain.model.aggregates;

import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Wines Aggregate Root
 *
 * @summary
 * The Wines class is an aggregate root representing wines in the system.
 */
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
    private String description;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String grapes;

    @Column(nullable = false)
    private Double alcohol;

    @Column
    private String certification;

    @Column
    private Double rating;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String producerId;

    @Column(nullable = false)
    private String batchId;

    @Column
    private String link;

    protected Wines() {}

    // Constructor for creating new wines
    public Wines(CreateWineCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.type = command.type();
        this.region = command.region();
        this.country = command.country();
        this.year = command.year();
        this.grapes = command.grapes();
        this.alcohol = command.alcohol();
        this.certification = command.certification();
        this.rating = command.rating();
        this.state = command.state();
        this.producerId = command.producerId();
        this.batchId = command.batchId();
        this.link = command.link();
    }

    public Wines updateWine(String name, String description, String type, String region, String country, int year,
                            String grapes, Double alcohol, String certification, Double rating, String state,
                            String producerId, String batchId, String link) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.region = region;
        this.country = country;
        this.year = year;
        this.grapes = grapes;
        this.alcohol = alcohol;
        this.certification = certification;
        this.rating = rating;
        this.state = state;
        this.producerId = producerId;
        this.batchId = batchId;
        this.link = link;
        return this;
    }
}
