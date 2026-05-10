package com.turkcell.library_management_cqrs.application.features.student.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;

@Component
public class DeleteStudentCommandHandler implements CommandHandler<DeleteStudentCommand, DeleteStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules rules;

    public DeleteStudentCommandHandler(StudentRepository studentRepository, StudentBusinessRules rules) {
        this.studentRepository = studentRepository;
        this.rules = rules;
    }

    @Override
    public DeleteStudentResponse handle(DeleteStudentCommand command) {
        var student = studentRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Student not found: " + command.id()));
        studentRepository.delete(student);
        return DeleteStudentResponse.create();
    }
}