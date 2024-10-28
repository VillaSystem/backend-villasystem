package com.example.backendvillasystem.batch_management.infrastructure.persistence.jpa;

import com.example.backendvillasystem.batch_management.domain.model.aggregates.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

}
