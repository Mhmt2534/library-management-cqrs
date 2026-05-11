package com.turkcell.library_management_cqrs.application.features.penalty.command.create;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CreatePenaltyResponse(
    UUID id,
    UUID borrowingId,
    BigDecimal amountTry,
    Instant penaltyDate,
    String paymentStatus
) {}
