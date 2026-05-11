package com.turkcell.library_management_cqrs.application.features.penalty.query.getall;

import org.springframework.data.domain.Page;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

public record GetAllPenaltiesQuery(int pageNumber, int pageSize) implements Query<Page<GetAllPenaltiesResponse>> {}
