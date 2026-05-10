package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;

public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
    Optional<BookCopy> findByCode(String code);
    List<BookCopy> findAllByBookId(UUID bookId);
}