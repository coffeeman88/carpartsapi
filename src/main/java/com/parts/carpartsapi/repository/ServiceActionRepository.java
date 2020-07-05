package com.parts.carpartsapi.repository;

import com.parts.carpartsapi.entity.ServiceAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ServiceActionRepository extends JpaRepository<ServiceAction, Long> {
    List<ServiceAction> findServiceActionByServStartDateBetween(LocalDate start, LocalDate end);
}
