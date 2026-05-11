package com.turkcell.library_management_cqrs.application.features.penalty.query.getall;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record GetAllPenaltiesResponse(
    UUID id,
    UUID borrowingId,
    BigDecimal amountTry,
    Instant penaltyDate,
    Instant paidAt,
    String paymentStatus
) {}
