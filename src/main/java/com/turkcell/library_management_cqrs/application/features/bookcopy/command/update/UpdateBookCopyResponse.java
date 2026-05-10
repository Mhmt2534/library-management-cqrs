package com.turkcell.library_management_cqrs.application.features.bookcopy.command.update;

import java.util.UUID;

public record UpdateBookCopyResponse(
    UUID id,
    String code,
    String status,
    UUID bookId
) {}