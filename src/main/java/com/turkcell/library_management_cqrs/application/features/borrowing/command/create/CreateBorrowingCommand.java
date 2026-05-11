package com.turkcell.library_management_cqrs.application.features.borrowing.command.create;

import java.time.Instant;
import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.borrowing.BorrowStatus;

import jakarta.validation.constraints.NotNull;

public record CreateBorrowingCommand(
    @NotNull(message = "Book copy ID cannot be null")
    UUID bookCopyId,
    @NotNull(message = "Student ID cannot be null")
    UUID studentId,
    @NotNull(message = "Borrowed by librarian ID cannot be null")
    UUID borrowedByLibrarianId
) implements Command<CreateBorrowingResponse> {}
