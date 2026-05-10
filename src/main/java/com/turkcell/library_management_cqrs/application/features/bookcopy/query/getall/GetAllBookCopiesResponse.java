package com.turkcell.library_management_cqrs.application.features.bookcopy.query.getall;

import java.util.UUID;

import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopyStatus;

public record GetAllBookCopiesResponse(
    UUID id,
    String code,
    BookCopyStatus status,
    UUID bookId
) {}