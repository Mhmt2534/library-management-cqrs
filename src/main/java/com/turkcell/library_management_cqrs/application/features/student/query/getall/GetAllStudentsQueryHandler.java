package com.turkcell.library_management_cqrs.application.features.student.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.student.mapper.StudentMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;

@Component
public class GetAllStudentsQueryHandler implements QueryHandler<GetAllStudentsQuery, Page<GetAllStudentsResponse>> {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    public GetAllStudentsQueryHandler(StudentRepository studentRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GetAllStudentsResponse> handle(GetAllStudentsQuery query) {
        Pageable pageable = PageRequest.of(query.pageNumber(), query.pageSize());
        var students = studentRepository.findAll(pageable);
        return students.map(mapper::getAllStudentsResponseFromStudent);
    }
}