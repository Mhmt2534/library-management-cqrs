package com.turkcell.library_management_cqrs.application.features.book.command.create;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.book.mapper.BookMapper;
import com.turkcell.library_management_cqrs.application.features.book.rule.BookBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.book.Book;
import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, CreateBookResponse> {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookBusinessRules rules;
    private final BookMapper mapper;

    public CreateBookCommandHandler(BookRepository bookRepository, CategoryRepository categoryRepository, BookBusinessRules rules, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public CreateBookResponse handle(CreateBookCommand command) {
        rules.bookWithSameNameAndAuthorMustNotExist(command.bookName(), command.authorName());
        rules.bookWithSameShelfNumberNotExist(command.shelfNumber());
        var categories = command.categoryIds().stream()
            .map(id -> categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found: " + id)))
            .collect(Collectors.toSet());

        Book book = mapper.bookFromCreateCommand(command);
        book.setCategories(categories);
        book = bookRepository.save(book);

        return mapper.createBookResponseFromBook(book);
    }
}