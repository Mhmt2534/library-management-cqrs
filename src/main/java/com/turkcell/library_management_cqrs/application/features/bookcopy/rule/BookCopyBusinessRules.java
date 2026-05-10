package com.turkcell.library_management_cqrs.application.features.bookcopy.rule;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;

@Component
public class BookCopyBusinessRules {

    private final BookCopyRepository bookCopyRepository;

    public BookCopyBusinessRules(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public void bookCopyCodeMustNotExist(String code) {
        var existing = bookCopyRepository.findByCode(code);
        if (existing.isPresent()) {
            throw new RuntimeException("Book copy code already exists");
        }
    }

    public void bookCopyCodeMustNotExist(String code, UUID currentId) {
        var existing = bookCopyRepository.findByCode(code);
        if (existing.isPresent() && !existing.get().getId().equals(currentId)) {
            throw new RuntimeException("Book copy code already exists");
        }
    }
}