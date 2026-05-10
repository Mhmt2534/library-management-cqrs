package com.turkcell.library_management_cqrs.application.features.bookcopy.command.create;

import java.util.Set;
import java.util.UUID;

public record CreateBookCopyResponse(
    UUID bookId,
    int createdCount,
    Set<UUID> createdIds
) {}