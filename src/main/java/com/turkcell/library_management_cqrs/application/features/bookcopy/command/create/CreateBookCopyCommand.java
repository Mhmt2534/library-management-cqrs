package com.turkcell.library_management_cqrs.application.features.bookcopy.command.create;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookCopyCommand(
    @NotNull(message = "Book ID cannot be null")
    UUID bookId,
    @NotBlank(message = "Code cannot be blank")
    String code
) implements Command<CreateBookCopyResponse> {}