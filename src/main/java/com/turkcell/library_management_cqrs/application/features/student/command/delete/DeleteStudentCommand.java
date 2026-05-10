package com.turkcell.library_management_cqrs.application.features.student.command.delete;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.NotNull;

public record DeleteStudentCommand(
    @NotNull(message = "ID cannot be null")
    UUID id
) implements Command<DeleteStudentResponse> {}