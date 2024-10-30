package com.example.backendvillasystem.wines.infrastructure.persistence.jpa;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WineRepository extends JpaRepository<Wines, Long> {
}
