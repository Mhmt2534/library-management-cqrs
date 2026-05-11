package com.turkcell.library_management_cqrs.application.features.penalty.command.update;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.penalty.PaymentStatus;

import jakarta.validation.constraints.NotNull;

public record UpdatePenaltyCommand(
    @NotNull(message = "Penalty ID cannot be null")
    UUID id,
    @NotNull(message = "Amount cannot be null")
    BigDecimal amountTry,
    @NotNull(message = "Payment status cannot be null")
    PaymentStatus paymentStatus,
    String description
) implements Command<UpdatePenaltyResponse> {}
