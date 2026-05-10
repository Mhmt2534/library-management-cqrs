package com.turkcell.library_management_cqrs.application.features.bookcopy.command.create;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_management_cqrs.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.book.Book;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;
import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;

@Component
public class CreateBookCopyCommandHandler implements CommandHandler<CreateBookCopyCommand, CreateBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BookCopyBusinessRules rules;
    private final BookCopyMapper mapper;

    public CreateBookCopyCommandHandler(BookCopyRepository bookCopyRepository, BookRepository bookRepository, BookCopyBusinessRules rules, BookCopyMapper mapper) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public CreateBookCopyResponse handle(CreateBookCopyCommand command) {
        Book book = bookRepository.findById(command.bookId()).orElseThrow(() -> new RuntimeException("Book not found: " + command.bookId()));

        rules.bookCopyCodeMustNotExist(command.code());

        BookCopy bookCopy = mapper.bookCopyFromCreateCommand(command, book);
        bookCopy = bookCopyRepository.save(bookCopy);

        return new CreateBookCopyResponse(book.getId(), 1, Set.of(bookCopy.getId()));
    }
}