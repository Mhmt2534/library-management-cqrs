package com.turkcell.library_management_cqrs.application.features.librarian.command.update;

import java.util.UUID;

public record UpdateLibrarianResponse(
    UUID id,
    String identityNumber,
    String librarianName,
    String librarianSurname,
    String phoneNumber,
    String shift
) {}
