package com.turkcell.library_management_cqrs.application.features.borrowing.command.create;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.borrowing.mapper.BorrowingMapper;
import com.turkcell.library_management_cqrs.application.features.borrowing.rule.BorrowingBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopyStatus;
import com.turkcell.library_management_cqrs.domain.borrowing.BorrowStatus;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;
import com.turkcell.library_management_cqrs.domain.student.Student;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;
import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;
import com.turkcell.library_management_cqrs.persistence.repository.BorrowingRepository;

@Component
public class CreateBorrowingCommandHandler implements CommandHandler<CreateBorrowingCommand, CreateBorrowingResponse> {

    private final BorrowingRepository borrowingRepository;
    private final BookCopyRepository bookCopyRepository;
    private final StudentRepository studentRepository;
    private final LibrarianRepository librarianRepository;
    private final BorrowingBusinessRules rules;
    private final BorrowingMapper mapper;

    public CreateBorrowingCommandHandler(BorrowingRepository borrowingRepository,
                                         BookCopyRepository bookCopyRepository,
                                         StudentRepository studentRepository,
                                         LibrarianRepository librarianRepository,
                                         BorrowingBusinessRules rules,
                                         BorrowingMapper mapper) {
        this.borrowingRepository = borrowingRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.studentRepository = studentRepository;
        this.librarianRepository = librarianRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public CreateBorrowingResponse handle(CreateBorrowingCommand command) {
        BookCopy bookCopy = bookCopyRepository.findById(command.bookCopyId())
            .orElseThrow(() -> new RuntimeException("Book copy not found: " + command.bookCopyId()));
        Student student = studentRepository.findById(command.studentId())
            .orElseThrow(() -> new RuntimeException("Student not found: " + command.studentId()));
        Librarian borrowedByLibrarian = librarianRepository.findById(command.borrowedByLibrarianId())
            .orElseThrow(() -> new RuntimeException("Borrowed by librarian not found: " + command.borrowedByLibrarianId()));

        rules.bookCopyMustBeAvailable(bookCopy);

        bookCopy.setStatus(BookCopyStatus.BORROWED);
        bookCopyRepository.save(bookCopy);

        var borrowing = mapper.borrowingFromCreateCommand(command, bookCopy, student, borrowedByLibrarian);

        borrowing.setBorrowStatus(BorrowStatus.BORROWED);
        borrowing.setDueAt(Instant.now().plus(15, ChronoUnit.DAYS));

        borrowing = borrowingRepository.save(borrowing);

        return mapper.createBorrowingResponseFromBorrowing(borrowing);
    }
}
