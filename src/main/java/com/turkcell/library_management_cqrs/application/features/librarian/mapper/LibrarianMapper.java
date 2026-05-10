package com.turkcell.library_management_cqrs.application.features.librarian.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.librarian.command.create.CreateLibrarianCommand;
import com.turkcell.library_management_cqrs.application.features.librarian.command.create.CreateLibrarianResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.command.update.UpdateLibrarianCommand;
import com.turkcell.library_management_cqrs.application.features.librarian.command.update.UpdateLibrarianResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.query.getall.GetAllLibrariansResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.query.getbyid.GetLibrarianByIdResponse;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;

@Component
public class LibrarianMapper {

    public Librarian librarianFromCreateCommand(CreateLibrarianCommand command) {
        Librarian librarian = new Librarian();
        librarian.setIdentityNumber(command.identityNumber());
        librarian.setLibrarianName(command.librarianName());
        librarian.setLibrarianSurname(command.librarianSurname());
        librarian.setPhoneNumber(command.phoneNumber());
        librarian.setShift(command.shift());
        return librarian;
    }

    public Librarian librarianFromUpdateCommand(Librarian librarian, UpdateLibrarianCommand command) {
        librarian.setIdentityNumber(command.identityNumber());
        librarian.setLibrarianName(command.librarianName());
        librarian.setLibrarianSurname(command.librarianSurname());
        librarian.setPhoneNumber(command.phoneNumber());
        librarian.setShift(command.shift());
        return librarian;
    }

    public CreateLibrarianResponse createLibrarianResponseFromLibrarian(Librarian librarian) {
        return new CreateLibrarianResponse(
            librarian.getId(),
            librarian.getIdentityNumber(),
            librarian.getLibrarianName(),
            librarian.getLibrarianSurname(),
            librarian.getPhoneNumber(),
            librarian.getShift().name()
        );
    }

    public UpdateLibrarianResponse updateLibrarianResponseFromLibrarian(Librarian librarian) {
        return new UpdateLibrarianResponse(
            librarian.getId(),
            librarian.getIdentityNumber(),
            librarian.getLibrarianName(),
            librarian.getLibrarianSurname(),
            librarian.getPhoneNumber(),
            librarian.getShift().name()
        );
    }

    public GetAllLibrariansResponse getAllLibrariansResponseFromLibrarian(Librarian librarian) {
        return new GetAllLibrariansResponse(
            librarian.getId(),
            librarian.getIdentityNumber(),
            librarian.getLibrarianName(),
            librarian.getLibrarianSurname(),
            librarian.getPhoneNumber(),
            librarian.getShift().name()
        );
    }

    public GetLibrarianByIdResponse getLibrarianByIdResponseFromLibrarian(Librarian librarian) {
        return new GetLibrarianByIdResponse(
            librarian.getId(),
            librarian.getIdentityNumber(),
            librarian.getLibrarianName(),
            librarian.getLibrarianSurname(),
            librarian.getPhoneNumber(),
            librarian.getShift().name()
        );
    }
}
