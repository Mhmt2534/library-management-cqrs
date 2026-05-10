package com.turkcell.library_management_cqrs.application.features.student.command.create;

import java.time.Instant;
import java.util.UUID;

public record CreateStudentResponse(
    UUID id,
    String studentName,
    String studentSurname,
    String phoneNumber,
    String identityNumber,
    Instant createdAt,
    String status
) {}