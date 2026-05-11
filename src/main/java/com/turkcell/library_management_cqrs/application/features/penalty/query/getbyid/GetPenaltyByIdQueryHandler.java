package com.turkcell.library_management_cqrs.application.features.penalty.query.getbyid;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.application.features.penalty.mapper.PenaltyMapper;
import com.turkcell.library_management_cqrs.persistence.repository.PenaltyRepository;

@Component
public class GetPenaltyByIdQueryHandler implements QueryHandler<GetPenaltyByIdQuery, GetPenaltyByIdResponse> {

    private final PenaltyRepository penaltyRepository;
    private final PenaltyMapper mapper;

    public GetPenaltyByIdQueryHandler(PenaltyRepository penaltyRepository, PenaltyMapper mapper) {
        this.penaltyRepository = penaltyRepository;
        this.mapper = mapper;
    }

    @Override
    public GetPenaltyByIdResponse handle(GetPenaltyByIdQuery query) {
        return penaltyRepository.findById(query.id())
            .map(mapper::getPenaltyByIdResponseFromPenalty)
            .orElseThrow(() -> new RuntimeException("Penalty not found: " + query.id()));
    }
}
