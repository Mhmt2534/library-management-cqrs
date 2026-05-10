package com.turkcell.library_management_cqrs.application.features.librarian.query.getall;

import java.util.UUID;

public record GetAllLibrariansResponse(
    UUID id,
    String identityNumber,
    String librarianName,
    String librarianSurname,
    String phoneNumber,
    String shift
) {}
