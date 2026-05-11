package com.turkcell.library_management_cqrs.application.features.penalty.command.create;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.penalty.PaymentStatus;

import jakarta.validation.constraints.NotNull;

public record CreatePenaltyCommand(
    @NotNull(message = "Borrowing ID cannot be null")
    UUID borrowingId,
    @NotNull(message = "Amount cannot be null")
    BigDecimal amountTry,
    @NotNull(message = "Payment status cannot be null")
    PaymentStatus paymentStatus,
    String description
) implements Command<CreatePenaltyResponse> {}
