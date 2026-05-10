package com.turkcell.library_management_cqrs.application.features.student.command.create;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.student.mapper.StudentMapper;
import com.turkcell.library_management_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.student.Student;
import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;

@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, CreateStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules rules;
    private final StudentMapper mapper;

    public CreateStudentCommandHandler(StudentRepository studentRepository, StudentBusinessRules rules, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public CreateStudentResponse handle(CreateStudentCommand command) {
        rules.studentWithSameIdentityNumberMustNotExist(command.identityNumber());
        rules.studentWithSamePhoneNumberMustNotExist(command.phoneNumber());

        Student student = mapper.studentFromCreateCommand(command);
        student.setCreatedAt(Instant.now());
        student = studentRepository.save(student);

        return mapper.createStudentResponseFromStudent(student);
    }
}