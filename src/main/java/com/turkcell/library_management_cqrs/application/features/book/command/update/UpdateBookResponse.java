package com.turkcell.library_management_cqrs.application.features.book.command.update;

import java.util.UUID;

public record UpdateBookResponse(
    UUID id,
    String bookName,
    String authorName,
    String shelfNumber,
    int quantityInStock
) {}