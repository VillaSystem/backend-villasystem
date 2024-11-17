package com.example.backendvillasystem.wines.domain.model.aggregates;

import com.example.backendvillasystem.wines.domain.model.commands.CreateWineCommand;
import com.example.backendvillasystem.wines.domain.model.commands.UpdateWineCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Wines extends AbstractAggregateRoot<Wines> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String type;

    private String region;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer year;

    @ElementCollection
    private List<String> grapes;

    private Double alcohol;
    private String certification;
    private Double rating;
    private String state;

    @Column(name = "producer_id", nullable = false)
    private String producerId;

    @Column(name = "batch_id", nullable = false)
    private String batchId;

    private String link;

    protected Wines() {}

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

    public void update(UpdateWineCommand command) {
        if (command.name() != null) this.name = command.name();
        if (command.description() != null) this.description = command.description();
        if (command.type() != null) this.type = command.type();
        if (command.region() != null) this.region = command.region();
        if (command.country() != null) this.country = command.country();
        if (command.year() != null) this.year = command.year();
        if (command.grapes() != null) this.grapes = command.grapes();
        if (command.alcohol() != null) this.alcohol = command.alcohol();
        if (command.certification() != null) this.certification = command.certification();
        if (command.rating() != null) this.rating = command.rating();
        if (command.state() != null) this.state = command.state();
        if (command.producerId() != null) this.producerId = command.producerId();
        if (command.batchId() != null) this.batchId = command.batchId();
        if (command.link() != null) this.link = command.link();
    }
}
