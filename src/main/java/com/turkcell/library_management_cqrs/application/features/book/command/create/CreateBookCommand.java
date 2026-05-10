package com.turkcell.library_management_cqrs.application.features.book.command.create;

import java.util.Set;
import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateBookCommand(
    @NotBlank(message = "Book name cannot be blank")
    String bookName,
    @NotBlank(message = "Author name cannot be blank")
    String authorName,
    String shelfNumber,
    @PositiveOrZero(message = "Quantity in stock must be positive or zero")
    int quantityInStock,
    @NotNull(message = "Category IDs cannot be null")
    Set<UUID> categoryIds
) implements Command<CreateBookResponse> {}