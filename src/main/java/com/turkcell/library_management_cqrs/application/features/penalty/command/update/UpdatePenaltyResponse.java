package com.turkcell.library_management_cqrs.application.features.penalty.command.update;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record UpdatePenaltyResponse(
    UUID id,
    UUID borrowingId,
    BigDecimal amountTry,
    Instant penaltyDate,
    Instant paidAt,
    String paymentStatus
) {}
