package com.turkcell.library_management_cqrs.application.features.penalty.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.penalty.mapper.PenaltyMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.penalty.Penalty;
import com.turkcell.library_management_cqrs.persistence.repository.PenaltyRepository;

@Component
public class UpdatePenaltyCommandHandler implements CommandHandler<UpdatePenaltyCommand, UpdatePenaltyResponse> {

    private final PenaltyRepository penaltyRepository;
    private final PenaltyMapper mapper;

    public UpdatePenaltyCommandHandler(PenaltyRepository penaltyRepository, PenaltyMapper mapper) {
        this.penaltyRepository = penaltyRepository;
        this.mapper = mapper;
    }

    @Override
    public UpdatePenaltyResponse handle(UpdatePenaltyCommand command) {
        Penalty penalty = penaltyRepository.findById(command.id())
            .orElseThrow(() -> new RuntimeException("Penalty not found: " + command.id()));

        penalty = mapper.penaltyFromUpdateCommand(penalty, command);
        penalty = penaltyRepository.save(penalty);

        return mapper.updatePenaltyResponseFromPenalty(penalty);
    }
}
