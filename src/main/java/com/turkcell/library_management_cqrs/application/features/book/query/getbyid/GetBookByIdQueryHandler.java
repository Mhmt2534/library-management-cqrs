package com.turkcell.library_management_cqrs.application.features.book.query.getbyid;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.book.mapper.BookMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;

@Component
public class GetBookByIdQueryHandler implements QueryHandler<GetBookByIdQuery, GetBookByIdResponse> {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public GetBookByIdQueryHandler(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public GetBookByIdResponse handle(GetBookByIdQuery query) {
        var book = bookRepository.findById(query.id()).orElseThrow(() -> new RuntimeException("Book not found: " + query.id()));
        return mapper.getBookByIdResponseFromBook(book);
    }
}