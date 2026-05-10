package com.turkcell.library_management_cqrs.application.features.student.query.getall;

import java.time.Instant;
import java.util.UUID;

public record GetAllStudentsResponse(
    UUID id,
    String studentName,
    String studentSurname,
    String phoneNumber,
    String identityNumber,
    Instant createdAt,
    String status
) {}