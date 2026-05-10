package com.turkcell.library_management_cqrs.application.features.librarian.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;

@Component
public class DeleteLibrarianCommandHandler implements CommandHandler<DeleteLibrarianCommand, DeleteLibrarianResponse> {

    private final LibrarianRepository librarianRepository;

    public DeleteLibrarianCommandHandler(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public DeleteLibrarianResponse handle(DeleteLibrarianCommand command) {
        librarianRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Librarian not found: " + command.id()));
        librarianRepository.deleteById(command.id());
        return new DeleteLibrarianResponse("Librarian deleted successfully");
    }
}
