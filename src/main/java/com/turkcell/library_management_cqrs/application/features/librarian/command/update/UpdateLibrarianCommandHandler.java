package com.turkcell.library_management_cqrs.application.features.librarian.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.librarian.mapper.LibrarianMapper;
import com.turkcell.library_management_cqrs.application.features.librarian.rule.LibrarianBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;

@Component
public class UpdateLibrarianCommandHandler implements CommandHandler<UpdateLibrarianCommand, UpdateLibrarianResponse> {

    private final LibrarianRepository librarianRepository;
    private final LibrarianBusinessRules rules;
    private final LibrarianMapper mapper;

    public UpdateLibrarianCommandHandler(LibrarianRepository librarianRepository, LibrarianBusinessRules rules, LibrarianMapper mapper) {
        this.librarianRepository = librarianRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public UpdateLibrarianResponse handle(UpdateLibrarianCommand command) {
        Librarian librarian = librarianRepository.findById(command.id())
            .orElseThrow(() -> new RuntimeException("Librarian not found: " + command.id()));

        rules.librarianWithSameIdentityNumberMustNotExist(command.identityNumber(), command.id());
        rules.librarianWithSamePhoneNumberMustNotExist(command.phoneNumber(), command.id());

        librarian = mapper.librarianFromUpdateCommand(librarian, command);
        librarian = librarianRepository.save(librarian);

        return mapper.updateLibrarianResponseFromLibrarian(librarian);
    }
}
