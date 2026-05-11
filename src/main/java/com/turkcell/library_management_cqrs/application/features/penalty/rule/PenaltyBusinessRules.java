package com.turkcell.library_management_cqrs.application.features.penalty.rule;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.persistence.repository.PenaltyRepository;

@Component
public class PenaltyBusinessRules {

    private final PenaltyRepository penaltyRepository;

    public PenaltyBusinessRules(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    public void penaltyMustNotExistForBorrowing(UUID borrowingId) {
        var existingPenalty = penaltyRepository.findByBorrowingId(borrowingId);
        if (existingPenalty.isPresent()) {
            throw new RuntimeException("Penalty already exists for this borrowing");
        }
    }
}
