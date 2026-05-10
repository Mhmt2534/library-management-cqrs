package com.turkcell.library_management_cqrs.application.features.bookcopy.query.getbyid;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

import jakarta.validation.constraints.NotNull;

public record GetBookCopyByIdQuery(
    @NotNull(message = "ID cannot be null")
    UUID id
) implements Query<GetBookCopyByIdResponse> {}