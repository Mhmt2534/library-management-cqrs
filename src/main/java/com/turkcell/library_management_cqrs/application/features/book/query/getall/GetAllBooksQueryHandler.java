package com.turkcell.library_management_cqrs.application.features.book.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.book.mapper.BookMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;

@Component
public class GetAllBooksQueryHandler implements QueryHandler<GetAllBooksQuery, Page<GetAllBooksResponse>> {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public GetAllBooksQueryHandler(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GetAllBooksResponse> handle(GetAllBooksQuery query) {
        Pageable pageable = PageRequest.of(query.pageNumber(), query.pageSize());
        var books = bookRepository.findAll(pageable);
        return books.map(mapper::getAllBooksResponseFromBook);
    }
}