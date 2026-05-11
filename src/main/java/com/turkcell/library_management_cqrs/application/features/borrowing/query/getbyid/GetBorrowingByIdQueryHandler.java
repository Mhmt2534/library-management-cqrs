package com.turkcell.library_management_cqrs.application.features.borrowing.query.getbyid;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.application.features.borrowing.mapper.BorrowingMapper;
import com.turkcell.library_management_cqrs.persistence.repository.BorrowingRepository;

@Component
public class GetBorrowingByIdQueryHandler implements QueryHandler<GetBorrowingByIdQuery, GetBorrowingByIdResponse> {

    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper mapper;

    public GetBorrowingByIdQueryHandler(BorrowingRepository borrowingRepository, BorrowingMapper mapper) {
        this.borrowingRepository = borrowingRepository;
        this.mapper = mapper;
    }

    @Override
    public GetBorrowingByIdResponse handle(GetBorrowingByIdQuery query) {
        return borrowingRepository.findById(query.id())
            .map(mapper::getBorrowingByIdResponseFromBorrowing)
            .orElseThrow(() -> new RuntimeException("Borrowing not found: " + query.id()));
    }
}
