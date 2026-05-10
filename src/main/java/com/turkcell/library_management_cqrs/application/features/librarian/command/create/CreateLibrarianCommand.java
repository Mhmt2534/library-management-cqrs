package com.turkcell.library_management_cqrs.application.features.librarian.command.create;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.librarian.Shift;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLibrarianCommand(
    @NotBlank(message = "Identity number cannot be blank")
    String identityNumber,
    @NotBlank(message = "Librarian name cannot be blank")
    String librarianName,
    @NotBlank(message = "Librarian surname cannot be blank")
    String librarianSurname,
    @NotBlank(message = "Phone number cannot be blank")
    String phoneNumber,
    @NotNull(message = "Shift cannot be null")
    Shift shift
) implements Command<CreateLibrarianResponse> {}
