package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, UUID> {
}
