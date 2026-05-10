package com.turkcell.library_management_cqrs.application.features.student.query.getbyid;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

import jakarta.validation.constraints.NotNull;

public record GetStudentByIdQuery(
    @NotNull(message = "ID cannot be null")
    UUID id
) implements Query<GetStudentByIdResponse> {}