package com.turkcell.library_management_cqrs.application.features.borrowing.command.update;

import java.time.Instant;
import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.borrowing.BorrowStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateBorrowingCommand(
    @NotNull(message = "Borrowing ID cannot be null")
    UUID id,
    UUID returnedToLibrarianId,
    @NotNull(message = "Borrow status cannot be null")
    BorrowStatus borrowStatus
) implements Command<UpdateBorrowingResponse> {}
