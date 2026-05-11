package com.turkcell.library_management_cqrs.application.features.borrowing.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.borrowing.command.create.CreateBorrowingCommand;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.create.CreateBorrowingResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.update.UpdateBorrowingCommand;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.update.UpdateBorrowingResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.query.getall.GetAllBorrowingsResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.query.getbyid.GetBorrowingByIdResponse;
import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;
import com.turkcell.library_management_cqrs.domain.student.Student;

@Component
public class BorrowingMapper {

    public Borrowing borrowingFromCreateCommand(CreateBorrowingCommand command, BookCopy bookCopy, Student student, Librarian borrowedByLibrarian) {
        Borrowing borrowing = new Borrowing();
        borrowing.setBookCopy(bookCopy);
        borrowing.setStudent(student);
        borrowing.setBorrowedByLibrarian(borrowedByLibrarian);
        borrowing.setBorrowedAt(Instant.now());
        return borrowing;
    }

    public Borrowing borrowingFromUpdateCommand(Borrowing borrowing, UpdateBorrowingCommand command, Librarian returnedToLibrarian) {
        borrowing.setReturnedToLibrarian(returnedToLibrarian);
        borrowing.setReturnedAt(Instant.now());
        borrowing.setBorrowStatus(command.borrowStatus());
        return borrowing;
    }

    public CreateBorrowingResponse createBorrowingResponseFromBorrowing(Borrowing borrowing) {
        return new CreateBorrowingResponse(
            borrowing.getId(),
            borrowing.getBookCopy().getId(),
            borrowing.getStudent().getId(),
            borrowing.getBorrowedByLibrarian().getId(),
            borrowing.getBorrowedAt(),
            borrowing.getDueAt(),
            borrowing.getBorrowStatus().name()
        );
    }

    public UpdateBorrowingResponse updateBorrowingResponseFromBorrowing(Borrowing borrowing) {
        return new UpdateBorrowingResponse(
            borrowing.getId(),
            borrowing.getBookCopy().getId(),
            borrowing.getStudent().getId(),
            borrowing.getBorrowedByLibrarian().getId(),
            borrowing.getReturnedToLibrarian() != null ? borrowing.getReturnedToLibrarian().getId() : null,
            borrowing.getBorrowedAt(),
            borrowing.getDueAt(),
            borrowing.getReturnedAt(),
            borrowing.getBorrowStatus().name()
        );
    }

    public GetAllBorrowingsResponse getAllBorrowingsResponseFromBorrowing(Borrowing borrowing) {
        return new GetAllBorrowingsResponse(
            borrowing.getId(),
            borrowing.getBookCopy().getId(),
            borrowing.getStudent().getId(),
            borrowing.getBorrowedByLibrarian().getId(),
            borrowing.getReturnedToLibrarian() != null ? borrowing.getReturnedToLibrarian().getId() : null,
            borrowing.getBorrowedAt(),
            borrowing.getDueAt(),
            borrowing.getReturnedAt(),
            borrowing.getBorrowStatus().name()
        );
    }

    public GetBorrowingByIdResponse getBorrowingByIdResponseFromBorrowing(Borrowing borrowing) {
        return new GetBorrowingByIdResponse(
            borrowing.getId(),
            borrowing.getBookCopy().getId(),
            borrowing.getStudent().getId(),
            borrowing.getBorrowedByLibrarian().getId(),
            borrowing.getReturnedToLibrarian() != null ? borrowing.getReturnedToLibrarian().getId() : null,
            borrowing.getBorrowedAt(),
            borrowing.getDueAt(),
            borrowing.getReturnedAt(),
            borrowing.getBorrowStatus().name()
        );
    }
}
