package com.turkcell.library_management_cqrs.application.features.librarian.query.getbyid;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

public record GetLibrarianByIdQuery(UUID id) implements Query<GetLibrarianByIdResponse> {}
