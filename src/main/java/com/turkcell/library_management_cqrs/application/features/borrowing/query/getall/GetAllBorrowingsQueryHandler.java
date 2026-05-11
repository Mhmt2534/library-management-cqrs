package com.turkcell.library_management_cqrs.application.features.borrowing.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BorrowingRepository;
import com.turkcell.library_management_cqrs.application.features.borrowing.mapper.BorrowingMapper;

@Component
public class GetAllBorrowingsQueryHandler implements QueryHandler<GetAllBorrowingsQuery, Page<GetAllBorrowingsResponse>> {

    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper mapper;

    public GetAllBorrowingsQueryHandler(BorrowingRepository borrowingRepository, BorrowingMapper mapper) {
        this.borrowingRepository = borrowingRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GetAllBorrowingsResponse> handle(GetAllBorrowingsQuery query) {
        var pageRequest = PageRequest.of(query.pageNumber(), query.pageSize());
        return borrowingRepository.findAll(pageRequest).map(mapper::getAllBorrowingsResponseFromBorrowing);
    }
}
