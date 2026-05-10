package com.turkcell.library_management_cqrs.application.features.librarian.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.librarian.mapper.LibrarianMapper;
import com.turkcell.library_management_cqrs.application.features.librarian.rule.LibrarianBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;

@Component
public class CreateLibrarianCommandHandler implements CommandHandler<CreateLibrarianCommand, CreateLibrarianResponse> {

    private final LibrarianRepository librarianRepository;
    private final LibrarianMapper mapper;
    private final LibrarianBusinessRules rules;

    public CreateLibrarianCommandHandler(LibrarianRepository librarianRepository, LibrarianMapper mapper, LibrarianBusinessRules rules) {
        this.librarianRepository = librarianRepository;
        this.mapper = mapper;
        this.rules = rules;
    }

    @Override
    public CreateLibrarianResponse handle(CreateLibrarianCommand command) {
        Librarian librarian = mapper.librarianFromCreateCommand(command);
        rules.librarianWithSameIdentityNumberMustNotExist(librarian.getIdentityNumber());
        rules.librarianWithSamePhoneNumberMustNotExist(librarian.getPhoneNumber());
        librarianRepository.save(librarian);
        return mapper.createLibrarianResponseFromLibrarian(librarian);
    }
}
