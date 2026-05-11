package com.turkcell.library_management_cqrs.application.features.penalty.query.getbyid;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

public record GetPenaltyByIdQuery(UUID id) implements Query<GetPenaltyByIdResponse> {}
