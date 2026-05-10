package com.turkcell.library_management_cqrs.application.features.librarian.query.getbyid;

import java.util.UUID;

public record GetLibrarianByIdResponse(
    UUID id,
    String identityNumber,
    String librarianName,
    String librarianSurname,
    String phoneNumber,
    String shift
) {}
