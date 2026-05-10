package com.turkcell.library_management_cqrs.application.features.student.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.student.mapper.StudentMapper;
import com.turkcell.library_management_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.student.Student;
import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;

@Component
public class UpdateStudentCommandHandler implements CommandHandler<UpdateStudentCommand, UpdateStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules rules;
    private final StudentMapper mapper;

    public UpdateStudentCommandHandler(StudentRepository studentRepository, StudentBusinessRules rules, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public UpdateStudentResponse handle(UpdateStudentCommand command) {
        Student student = studentRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Student not found: " + command.id()));

        

        student = mapper.studentFromUpdateCommand(student, command);
        student = studentRepository.save(student);

        return mapper.updateStudentResponseFromStudent(student);
    }
}