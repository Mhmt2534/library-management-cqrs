package com.turkcell.library_management_cqrs.application.features.bookcopy.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.bookcopy.mapper.BookCopyMapper;
import com.turkcell.library_management_cqrs.application.features.bookcopy.rule.BookCopyBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;

@Component
public class UpdateBookCopyCommandHandler implements CommandHandler<UpdateBookCopyCommand, UpdateBookCopyResponse> {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyBusinessRules rules;
    private final BookCopyMapper mapper;

    public UpdateBookCopyCommandHandler(BookCopyRepository bookCopyRepository, BookCopyBusinessRules rules, BookCopyMapper mapper) {
        this.bookCopyRepository = bookCopyRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public UpdateBookCopyResponse handle(UpdateBookCopyCommand command) {
        BookCopy bookCopy = bookCopyRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Book copy not found: " + command.id()));
        rules.bookCopyCodeMustNotExist(command.code(), command.id());

        bookCopy = mapper.bookCopyFromUpdateCommand(bookCopy, command);
        bookCopy = bookCopyRepository.save(bookCopy);

        return mapper.updateBookCopyResponseFromBookCopy(bookCopy);
    }
}