package com.turkcell.library_management_cqrs.application.features.borrowing.command.update;

import java.time.Instant;
import java.util.UUID;

public record UpdateBorrowingResponse(
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
