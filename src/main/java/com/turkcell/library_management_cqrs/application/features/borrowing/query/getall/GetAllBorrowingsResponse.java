package com.turkcell.library_management_cqrs.application.features.borrowing.query.getall;

import java.time.Instant;
import java.util.UUID;

public record GetAllBorrowingsResponse(
    UUID id,
    UUID bookCopyId,
    UUID studentId,
    UUID borrowedByLibrarianId,
    UUID returnedToLibrarianId,
    Instant borrowedAt,
    Instant dueAt,
    Instant returnedAt,
    String borrowStatus
) {}
