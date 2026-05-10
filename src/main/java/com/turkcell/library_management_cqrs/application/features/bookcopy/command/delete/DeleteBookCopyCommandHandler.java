package com.turkcell.library_management_cqrs.application.features.bookcopy.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;

@Component
public class DeleteBookCopyCommandHandler implements CommandHandler<DeleteBookCopyCommand, DeleteBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyBusinessRules rules;

    public DeleteBookCopyCommandHandler(BookCopyRepository bookCopyRepository, BookCopyBusinessRules rules) {
        this.bookCopyRepository = bookCopyRepository;
        this.rules = rules;
    }

    @Override
    public DeleteBookCopyResponse handle(DeleteBookCopyCommand command) {
        var bookCopy = bookCopyRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Book copy not found: " + command.id()));
        bookCopyRepository.delete(bookCopy);
        return DeleteBookCopyResponse.create();
    }
}