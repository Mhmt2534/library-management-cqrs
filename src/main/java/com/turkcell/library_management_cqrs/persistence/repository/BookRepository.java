package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.book.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    public Optional<Book> findByBookNameAndAuthorName(String bookName, String authorName);
}