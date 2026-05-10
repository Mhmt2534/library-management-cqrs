package com.turkcell.library_management_cqrs.application.features.student.query.getbyid;

import java.time.Instant;
import java.util.UUID;

public record GetStudentByIdResponse(
    UUID id,
    String studentName,
    String studentSurname,
    String phoneNumber,
    String identityNumber,
    Instant createdAt,
    String status
) {}