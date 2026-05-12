package com.turkcell.library_management_cqrs.application.features.librarian.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;
import com.turkcell.library_management_cqrs.application.features.librarian.mapper.LibrarianMapper;

@Component
public class GetAllLibrariansQueryHandler implements QueryHandler<GetAllLibrariansQuery, Page<GetAllLibrariansResponse>> {

    private final LibrarianRepository librarianRepository;
    private final LibrarianMapper mapper;

    public GetAllLibrariansQueryHandler(LibrarianRepository librarianRepository, LibrarianMapper mapper) {
        this.librarianRepository = librarianRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GetAllLibrariansResponse> handle(GetAllLibrariansQuery query) {
        PageRequest pageRequest = PageRequest.of(query.pageNumber(), query.pageSize());

        try {
            Thread.sleep(4000); // Simulate a long-running operation for performance testing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return librarianRepository.findAll(pageRequest).map(mapper::getAllLibrariansResponseFromLibrarian);
    }
}
