package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.librarian.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, UUID> {
    Optional<Librarian> findByIdentityNumber(String identityNumber);
    Optional<Librarian> findByPhoneNumber(String phoneNumber);
}
