package com.turkcell.library_management_cqrs.application.features.book.query.getall;

import java.util.UUID;

public record GetAllBooksResponse(
    UUID id,
    String bookName,
    String authorName,
    String shelfNumber,
    int quantityInStock
) {}