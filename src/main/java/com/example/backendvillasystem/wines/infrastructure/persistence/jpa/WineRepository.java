package com.example.backendvillasystem.wines.infrastructure.persistence.jpa;

import com.example.backendvillasystem.wines.domain.model.aggregates.Wines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineRepository extends JpaRepository<Wines, Long> {
    List<Wines> findByCountry(String country);
}
