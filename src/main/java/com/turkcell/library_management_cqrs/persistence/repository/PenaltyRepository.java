package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.penalty.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, UUID> {
    Optional<Penalty> findByBorrowingId(UUID borrowingId);
}
