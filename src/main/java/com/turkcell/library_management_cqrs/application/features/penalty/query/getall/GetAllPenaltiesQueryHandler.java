package com.turkcell.library_management_cqrs.application.features.penalty.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.PenaltyRepository;
import com.turkcell.library_management_cqrs.application.features.penalty.mapper.PenaltyMapper;

@Component
public class GetAllPenaltiesQueryHandler implements QueryHandler<GetAllPenaltiesQuery, Page<GetAllPenaltiesResponse>> {

    private final PenaltyRepository penaltyRepository;
    private final PenaltyMapper mapper;

    public GetAllPenaltiesQueryHandler(PenaltyRepository penaltyRepository, PenaltyMapper mapper) {
        this.penaltyRepository = penaltyRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GetAllPenaltiesResponse> handle(GetAllPenaltiesQuery query) {
        var pageRequest = PageRequest.of(query.pageNumber(), query.pageSize());
        return penaltyRepository.findAll(pageRequest).map(mapper::getAllPenaltiesResponseFromPenalty);
    }
}
