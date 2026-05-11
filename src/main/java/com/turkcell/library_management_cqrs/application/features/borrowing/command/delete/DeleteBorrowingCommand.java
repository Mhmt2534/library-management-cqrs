package com.turkcell.library_management_cqrs.application.features.borrowing.command.delete;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

public record DeleteBorrowingCommand(UUID id) implements Command<DeleteBorrowingResponse> {}
