package com.turkcell.library_management_cqrs.application.features.penalty.mapper;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.penalty.command.create.CreatePenaltyCommand;
import com.turkcell.library_management_cqrs.application.features.penalty.command.create.CreatePenaltyResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.command.update.UpdatePenaltyCommand;
import com.turkcell.library_management_cqrs.application.features.penalty.command.update.UpdatePenaltyResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.query.getall.GetAllPenaltiesResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.query.getbyid.GetPenaltyByIdResponse;
import com.turkcell.library_management_cqrs.domain.penalty.Penalty;
import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;

@Component
public class PenaltyMapper {

    public Penalty penaltyFromCreateCommand(CreatePenaltyCommand command, Borrowing borrowing) {
        Penalty penalty = new Penalty();
        penalty.setBorrowing(borrowing);
        penalty.setAmountTry(command.amountTry());
        penalty.setPenaltyDate(Instant.now());
        penalty.setPaymentStatus(command.paymentStatus());
        penalty.setDescription(command.description());
        return penalty;
    }

    public Penalty penaltyFromUpdateCommand(Penalty penalty, UpdatePenaltyCommand command) {
        penalty.setAmountTry(command.amountTry());
        penalty.setPaidAt(Instant.now());
        penalty.setPaymentStatus(command.paymentStatus());
        penalty.setDescription(command.description());
        return penalty;
    }

    public CreatePenaltyResponse createPenaltyResponseFromPenalty(Penalty penalty) {
        return new CreatePenaltyResponse(
            penalty.getId(),
            penalty.getBorrowing().getId(),
            penalty.getAmountTry(),
            penalty.getPenaltyDate(),
            penalty.getPaymentStatus().name()
        );
    }

    public UpdatePenaltyResponse updatePenaltyResponseFromPenalty(Penalty penalty) {
        return new UpdatePenaltyResponse(
            penalty.getId(),
            penalty.getBorrowing().getId(),
            penalty.getAmountTry(),
            penalty.getPenaltyDate(),
            penalty.getPaidAt(),
            penalty.getPaymentStatus().name()
        );
    }

    public GetAllPenaltiesResponse getAllPenaltiesResponseFromPenalty(Penalty penalty) {
        return new GetAllPenaltiesResponse(
            penalty.getId(),
            penalty.getBorrowing().getId(),
            penalty.getAmountTry(),
            penalty.getPenaltyDate(),
            penalty.getPaidAt(),
            penalty.getPaymentStatus().name()
        );
    }

    public GetPenaltyByIdResponse getPenaltyByIdResponseFromPenalty(Penalty penalty) {
        return new GetPenaltyByIdResponse(
            penalty.getId(),
            penalty.getBorrowing().getId(),
            penalty.getAmountTry(),
            penalty.getPenaltyDate(),
            penalty.getPaidAt(),
            penalty.getPaymentStatus().name()
        );
    }
}
