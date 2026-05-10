package com.turkcell.library_management_cqrs.application.features.librarian.command.delete;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

public record DeleteLibrarianCommand(UUID id) implements Command<DeleteLibrarianResponse> {}
