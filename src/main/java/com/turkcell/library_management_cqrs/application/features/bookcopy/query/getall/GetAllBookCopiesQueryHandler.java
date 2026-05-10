package com.turkcell.library_management_cqrs.application.features.bookcopy.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;

@Component
public class GetAllBookCopiesQueryHandler implements QueryHandler<GetAllBookCopiesQuery, Page<GetAllBookCopiesResponse>> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper mapper;

    public GetAllBookCopiesQueryHandler(BookCopyRepository bookCopyRepository, BookCopyMapper mapper) {
        this.bookCopyRepository = bookCopyRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GetAllBookCopiesResponse> handle(GetAllBookCopiesQuery query) {
        Pageable pageable = PageRequest.of(query.pageNumber(), query.pageSize());
        var copies = bookCopyRepository.findAll(pageable);
        return copies.map(mapper::getAllBookCopiesResponseFromBookCopy);
    }
}