package com.turkcell.library_management_cqrs.application.features.student.command.delete;

public record DeleteStudentResponse(
    String message
) {
    public static DeleteStudentResponse create() {
        return new DeleteStudentResponse("Student deleted successfully");
    }
}