package com.example.backendvillasystem.batch_management.domain.model.aggregates;

import com.example.backendvillasystem.batch_management.domain.model.commands.CreateBatchCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter

public class Batch extends AbstractAggregateRoot<Batch> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String batchNumber;

    @Column(nullable = false)
    private String grape;

    @Column(nullable = false)
    private String starDate;

    @Column(nullable = false)
    private String litersQuantity;

    @Column(nullable = false)
    private String ph;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private String processStatus;

    @Column(nullable = false)
    private String producerId;

    protected Batch() {}

    public Batch(CreateBatchCommand command) {
        this.batchNumber = command.batchNumber();
        this.grape = command.grape();
        this.starDate = command.starDate();
        this.litersQuantity = command.litersQuantity();
        this.ph = command.ph();
        this.temperature = command.temperature();
        this.processStatus = command.processStatus();
        this.producerId = command.producerId();
    }
}
