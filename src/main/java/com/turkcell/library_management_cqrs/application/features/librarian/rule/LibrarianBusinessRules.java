package com.turkcell.library_management_cqrs.application.features.librarian.rule;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;

@Component
public class LibrarianBusinessRules {

    private final LibrarianRepository librarianRepository;

    public LibrarianBusinessRules(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public void librarianWithSameIdentityNumberMustNotExist(String identityNumber) {
        var existingLibrarian = librarianRepository.findByIdentityNumber(identityNumber);
        if (existingLibrarian.isPresent()) {
            throw new RuntimeException("Librarian with same identity number already exists");
        }
    }

    public void librarianWithSameIdentityNumberMustNotExist(String identityNumber, UUID currentId) {
        var existingLibrarian = librarianRepository.findByIdentityNumber(identityNumber);
        if (existingLibrarian.isPresent() && !existingLibrarian.get().getId().equals(currentId)) {
            throw new RuntimeException("Librarian with same identity number already exists");
        }
    }

    public void librarianWithSamePhoneNumberMustNotExist(String phoneNumber) {
        var existingLibrarian = librarianRepository.findByPhoneNumber(phoneNumber);
        if (existingLibrarian.isPresent()) {
            throw new RuntimeException("Librarian with same phone number already exists");
        }
    }

    public void librarianWithSamePhoneNumberMustNotExist(String phoneNumber, UUID currentId) {
        var existingLibrarian = librarianRepository.findByPhoneNumber(phoneNumber);
        if (existingLibrarian.isPresent() && !existingLibrarian.get().getId().equals(currentId)) {
            throw new RuntimeException("Librarian with same phone number already exists");
        }
    }
}
