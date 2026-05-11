package com.turkcell.library_management_cqrs.application.features.penalty.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.PenaltyRepository;

@Component
public class DeletePenaltyCommandHandler implements CommandHandler<DeletePenaltyCommand, DeletePenaltyResponse> {

    private final PenaltyRepository penaltyRepository;

    public DeletePenaltyCommandHandler(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public DeletePenaltyResponse handle(DeletePenaltyCommand command) {
        penaltyRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Penalty not found: " + command.id()));
        penaltyRepository.deleteById(command.id());
        return DeletePenaltyResponse.create();
    }
}
