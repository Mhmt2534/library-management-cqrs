package com.turkcell.library_management_cqrs.application.features.student.query.getbyid;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.student.mapper.StudentMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;

@Component
public class GetStudentByIdQueryHandler implements QueryHandler<GetStudentByIdQuery, GetStudentByIdResponse> {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    public GetStudentByIdQueryHandler(StudentRepository studentRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public GetStudentByIdResponse handle(GetStudentByIdQuery query) {
        var student = studentRepository.findById(query.id()).orElseThrow(() -> new RuntimeException("Student not found: " + query.id()));
        return mapper.getStudentByIdResponseFromStudent(student);
    }
}