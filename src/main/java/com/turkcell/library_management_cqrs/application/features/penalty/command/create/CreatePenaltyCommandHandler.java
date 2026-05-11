package com.turkcell.library_management_cqrs.application.features.penalty.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.penalty.mapper.PenaltyMapper;
import com.turkcell.library_management_cqrs.application.features.penalty.rule.PenaltyBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;
import com.turkcell.library_management_cqrs.domain.penalty.Penalty;
import com.turkcell.library_management_cqrs.persistence.repository.BorrowingRepository;
import com.turkcell.library_management_cqrs.persistence.repository.PenaltyRepository;

@Component
public class CreatePenaltyCommandHandler implements CommandHandler<CreatePenaltyCommand, CreatePenaltyResponse> {

    private final PenaltyRepository penaltyRepository;
    private final BorrowingRepository borrowingRepository;
    private final PenaltyBusinessRules rules;
    private final PenaltyMapper mapper;

    public CreatePenaltyCommandHandler(PenaltyRepository penaltyRepository,
                                       BorrowingRepository borrowingRepository,
                                       PenaltyBusinessRules rules,
                                       PenaltyMapper mapper) {
        this.penaltyRepository = penaltyRepository;
        this.borrowingRepository = borrowingRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public CreatePenaltyResponse handle(CreatePenaltyCommand command) {
        Borrowing borrowing = borrowingRepository.findById(command.borrowingId())
            .orElseThrow(() -> new RuntimeException("Borrowing not found: " + command.borrowingId()));

        rules.penaltyMustNotExistForBorrowing(command.borrowingId());

        Penalty penalty = mapper.penaltyFromCreateCommand(command, borrowing);
        penalty = penaltyRepository.save(penalty);

        return mapper.createPenaltyResponseFromPenalty(penalty);
    }
}
