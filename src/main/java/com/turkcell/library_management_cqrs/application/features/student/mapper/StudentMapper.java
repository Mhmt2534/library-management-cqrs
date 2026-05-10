package com.turkcell.library_management_cqrs.application.features.student.mapper;

import com.turkcell.library_management_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_management_cqrs.application.features.student.command.create.CreateStudentResponse;
import com.turkcell.library_management_cqrs.application.features.student.command.update.UpdateStudentCommand;
import com.turkcell.library_management_cqrs.application.features.student.command.update.UpdateStudentResponse;
import com.turkcell.library_management_cqrs.application.features.student.query.getall.GetAllStudentsResponse;
import com.turkcell.library_management_cqrs.application.features.student.query.getbyid.GetStudentByIdResponse;
import com.turkcell.library_management_cqrs.domain.student.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student studentFromCreateCommand(CreateStudentCommand command) {
        Student student = new Student();
        student.setStudentName(command.studentName());
        student.setStudentSurname(command.studentSurname());
        student.setPhoneNumber(command.phoneNumber());
        student.setIdentityNumber(command.identityNumber());
        student.setStatus(command.status());
        return student;
    }

    public Student studentFromUpdateCommand(Student student, UpdateStudentCommand command) {
        student.setStudentName(command.studentName());
        student.setStudentSurname(command.studentSurname());
        student.setPhoneNumber(command.phoneNumber());
        student.setIdentityNumber(command.identityNumber());
        student.setStatus(command.status());
        return student;
    }

    public CreateStudentResponse createStudentResponseFromStudent(Student student) {
        return new CreateStudentResponse(
            student.getId(),
            student.getStudentName(),
            student.getStudentSurname(),
            student.getPhoneNumber(),
            student.getIdentityNumber(),
            student.getCreatedAt(),
            student.getStatus().name()
        );
    }

    public UpdateStudentResponse updateStudentResponseFromStudent(Student student) {
        return new UpdateStudentResponse(
            student.getId(),
            student.getStudentName(),
            student.getStudentSurname(),
            student.getPhoneNumber(),
            student.getIdentityNumber(),
            student.getCreatedAt(),
            student.getStatus().name()
        );
    }

    public GetAllStudentsResponse getAllStudentsResponseFromStudent(Student student) {
        return new GetAllStudentsResponse(
            student.getId(),
            student.getStudentName(),
            student.getStudentSurname(),
            student.getPhoneNumber(),
            student.getIdentityNumber(),
            student.getCreatedAt(),
            student.getStatus().name()
        );
    }

    public GetStudentByIdResponse getStudentByIdResponseFromStudent(Student student) {
        return new GetStudentByIdResponse(
            student.getId(),
            student.getStudentName(),
            student.getStudentSurname(),
            student.getPhoneNumber(),
            student.getIdentityNumber(),
            student.getCreatedAt(),
            student.getStatus().name()
        );
    }
}