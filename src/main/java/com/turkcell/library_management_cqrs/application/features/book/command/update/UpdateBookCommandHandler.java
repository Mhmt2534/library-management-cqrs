package com.turkcell.library_management_cqrs.application.features.book.command.update;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.book.mapper.BookMapper;
import com.turkcell.library_management_cqrs.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class UpdateBookCommandHandler implements CommandHandler<UpdateBookCommand, UpdateBookResponse> {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookBusinessRules rules;
    private final BookMapper mapper;

    public UpdateBookCommandHandler(BookRepository bookRepository, CategoryRepository categoryRepository, BookBusinessRules rules, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public UpdateBookResponse handle(UpdateBookCommand command) {
        var book = bookRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Book not found: " + command.id()));

        rules.bookWithSameNameAndAuthorMustNotExist(command.bookName(), command.authorName());

        var categories = command.categoryIds().stream()
            .map(id -> categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found: " + id)))
            .collect(Collectors.toSet());

        book = mapper.bookFromUpdateCommand(book, command);
        book.setCategories(categories);
        book = bookRepository.save(book);

        return mapper.updateBookResponseFromBook(book);
    }
}