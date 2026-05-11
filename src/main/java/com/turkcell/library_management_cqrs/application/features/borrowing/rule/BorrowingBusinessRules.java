package com.turkcell.library_management_cqrs.application.features.borrowing.rule;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopyStatus;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;

@Component
public class BorrowingBusinessRules {

    private final BookCopyRepository bookCopyRepository;

    public BorrowingBusinessRules(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public void bookCopyMustBeAvailable(BookCopy bookCopy) {
        if (bookCopy.getStatus() != BookCopyStatus.AVAILABLE) {
            throw new RuntimeException("Book copy is not available for borrowing");
        }
    }

    public void bookCopyMustBeAvailable(UUID bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
            .orElseThrow(() -> new RuntimeException("Book copy not found: " + bookCopyId));
        bookCopyMustBeAvailable(bookCopy);
    }
}
