package com.turkcell.library_management_cqrs.application.features.student.command.update;

import java.time.Instant;
import java.util.UUID;

public record UpdateStudentResponse(
    UUID id,
    String studentName,
    String studentSurname,
    String phoneNumber,
    String identityNumber,
    Instant createdAt,
    String status
) {}