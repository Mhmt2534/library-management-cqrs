package com.turkcell.library_management_cqrs.application.features.bookcopy.command.update;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopyStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBookCopyCommand(
    @NotNull(message = "ID cannot be null")
    UUID id,
    @NotBlank(message = "Code cannot be blank")
    String code,
    @NotNull(message = "Status cannot be null")
    BookCopyStatus status
) implements Command<UpdateBookCopyResponse> {}