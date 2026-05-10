package com.turkcell.library_management_cqrs.application.features.book.rule;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.persistence.repository.BookRepository;

@Component
public class BookBusinessRules {

    private final BookRepository bookRepository;

    public BookBusinessRules(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void bookWithSameNameAndAuthorMustNotExist(String bookName, String authorName) {
        var existingBook = bookRepository.findByBookNameAndAuthorName(bookName, authorName);
        if (existingBook.isPresent()) {
            throw new RuntimeException("Book with same name and author already exists");
        }
    }
}