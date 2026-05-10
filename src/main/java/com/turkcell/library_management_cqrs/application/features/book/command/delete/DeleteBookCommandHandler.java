package com.turkcell.library_management_cqrs.application.features.book.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;

@Component
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookCommand, DeleteBookResponse> {

    private final BookRepository bookRepository;
    private final BookBusinessRules rules;

    public DeleteBookCommandHandler(BookRepository bookRepository, BookBusinessRules rules) {
        this.bookRepository = bookRepository;
        this.rules = rules;
    }

    @Override
    public DeleteBookResponse handle(DeleteBookCommand command) {
        var book = bookRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Book not found: " + command.id()));
        bookRepository.delete(book);
        return DeleteBookResponse.create();
    }
}