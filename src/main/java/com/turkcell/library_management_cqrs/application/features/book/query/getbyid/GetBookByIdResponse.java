package com.turkcell.library_management_cqrs.application.features.book.query.getbyid;

import java.util.Set;
import java.util.UUID;

public record GetBookByIdResponse(
    UUID id,
    String bookName,
    String authorName,
    String shelfNumber,
    int quantityInStock,
    Set<String> categoryNames
) {}