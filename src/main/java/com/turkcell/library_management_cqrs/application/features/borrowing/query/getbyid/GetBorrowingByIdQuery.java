package com.turkcell.library_management_cqrs.application.features.borrowing.query.getbyid;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

public record GetBorrowingByIdQuery(UUID id) implements Query<GetBorrowingByIdResponse> {}
