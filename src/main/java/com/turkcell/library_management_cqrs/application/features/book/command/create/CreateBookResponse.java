package com.turkcell.library_management_cqrs.application.features.book.command.create;

import java.util.UUID;

public record CreateBookResponse(
    UUID id,
    String bookName,
    String authorName,
    String shelfNumber,
    int quantityInStock
) {}