package com.turkcell.library_management_cqrs.application.features.librarian.command.create;

import java.util.UUID;

public record CreateLibrarianResponse(
    UUID id,
    String identityNumber,
    String librarianName,
    String librarianSurname,
    String phoneNumber,
    String shift
) {}
