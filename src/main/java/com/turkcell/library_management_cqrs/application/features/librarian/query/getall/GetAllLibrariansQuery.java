package com.turkcell.library_management_cqrs.application.features.librarian.query.getall;

import org.springframework.data.domain.Page;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;

public record GetAllLibrariansQuery(int pageNumber, int pageSize) implements Query<Page<GetAllLibrariansResponse>> {}
