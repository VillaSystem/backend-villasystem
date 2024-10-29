package com.example.backendvillasystem.batch_management.infrastructure.persistence.jpa;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByProducerId(String producerId);

}
