package com.turkcell.library_management_cqrs.application.features.bookcopy.query.getbyid;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;

@Component
public class GetBookCopyByIdQueryHandler implements QueryHandler<GetBookCopyByIdQuery, GetBookCopyByIdResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper mapper;

    public GetBookCopyByIdQueryHandler(BookCopyRepository bookCopyRepository, BookCopyMapper mapper) {
        this.bookCopyRepository = bookCopyRepository;
        this.mapper = mapper;
    }

    @Override
    public GetBookCopyByIdResponse handle(GetBookCopyByIdQuery query) {
        var bookCopy = bookCopyRepository.findById(query.id()).orElseThrow(() -> new RuntimeException("Book copy not found: " + query.id()));
        return mapper.getBookCopyByIdResponseFromBookCopy(bookCopy);
    }
}