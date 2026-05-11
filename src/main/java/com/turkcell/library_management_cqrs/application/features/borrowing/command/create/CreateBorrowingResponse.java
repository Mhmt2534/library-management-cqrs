package com.turkcell.library_management_cqrs.application.features.borrowing.command.create;

import java.time.Instant;
import java.util.UUID;

public record CreateBorrowingResponse(
    UUID id,
    UUID bookCopyId,
    UUID studentId,
    UUID borrowedByLibrarianId,
    Instant borrowedAt,
    Instant dueAt,
    String borrowStatus
) {}
