package com.turkcell.library_management_cqrs.web.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_management_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_management_cqrs.application.features.student.command.create.CreateStudentResponse;
import com.turkcell.library_management_cqrs.application.features.student.command.delete.DeleteStudentCommand;
import com.turkcell.library_management_cqrs.application.features.student.command.delete.DeleteStudentResponse;
import com.turkcell.library_management_cqrs.application.features.student.command.update.UpdateStudentCommand;
import com.turkcell.library_management_cqrs.application.features.student.command.update.UpdateStudentResponse;
import com.turkcell.library_management_cqrs.application.features.student.query.getall.GetAllStudentsQuery;
import com.turkcell.library_management_cqrs.application.features.student.query.getall.GetAllStudentsResponse;
import com.turkcell.library_management_cqrs.application.features.student.query.getbyid.GetStudentByIdQuery;
import com.turkcell.library_management_cqrs.application.features.student.query.getbyid.GetStudentByIdResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    private final Mediator mediator;

    public StudentsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public CreateStudentResponse create(@Valid @RequestBody CreateStudentCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/update")
    public UpdateStudentResponse update(@Valid @RequestBody UpdateStudentCommand command) {
        return mediator.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteStudentResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeleteStudentCommand(id));
    }

    @GetMapping("/getAll")
    public Page<GetAllStudentsResponse> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return mediator.send(new GetAllStudentsQuery(pageNumber, pageSize));
    }

    @GetMapping("/getById/{id}")
    public GetStudentByIdResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetStudentByIdQuery(id));
    }
}