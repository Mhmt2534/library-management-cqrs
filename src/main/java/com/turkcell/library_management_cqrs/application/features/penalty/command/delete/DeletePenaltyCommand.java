package com.turkcell.library_management_cqrs.application.features.penalty.command.delete;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

public record DeletePenaltyCommand(UUID id) implements Command<DeletePenaltyResponse> {}
