package com.turkcell.library_management_cqrs.application.features.student.command.create;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.domain.student.StudentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStudentCommand(
    @NotBlank(message = "Student name cannot be blank")
    String studentName,
    @NotBlank(message = "Student surname cannot be blank")
    String studentSurname,
    @NotBlank(message = "Phone number cannot be blank")
    String phoneNumber,
    @NotBlank(message = "Identity number cannot be blank")
    String identityNumber,
    @NotNull(message = "Status cannot be null")
    StudentStatus status
) implements Command<CreateStudentResponse> {}