package com.turkcell.library_management_cqrs.application.features.librarian.query.getbyid;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;
import com.turkcell.library_management_cqrs.application.features.librarian.mapper.LibrarianMapper;

@Component
public class GetLibrarianByIdQueryHandler implements QueryHandler<GetLibrarianByIdQuery, GetLibrarianByIdResponse> {

    private final LibrarianRepository librarianRepository;
    private final LibrarianMapper mapper;

    public GetLibrarianByIdQueryHandler(LibrarianRepository librarianRepository, LibrarianMapper mapper) {
        this.librarianRepository = librarianRepository;
        this.mapper = mapper;
    }

    @Override
    public GetLibrarianByIdResponse handle(GetLibrarianByIdQuery query) {
        return librarianRepository.findById(query.id())
            .map(mapper::getLibrarianByIdResponseFromLibrarian)
            .orElseThrow(() -> new RuntimeException("Librarian not found: " + query.id()));
    }
}
