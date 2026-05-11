package com.turkcell.library_management_cqrs.application.features.borrowing.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.borrowing.mapper.BorrowingMapper;
import com.turkcell.library_management_cqrs.application.features.borrowing.rule.BorrowingBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;
import com.turkcell.library_management_cqrs.domain.borrowing.BorrowStatus;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopyStatus;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;
import com.turkcell.library_management_cqrs.persistence.repository.BookCopyRepository;
import com.turkcell.library_management_cqrs.persistence.repository.BorrowingRepository;
import com.turkcell.library_management_cqrs.persistence.repository.LibrarianRepository;

@Component
public class UpdateBorrowingCommandHandler implements CommandHandler<UpdateBorrowingCommand, UpdateBorrowingResponse> {

    private final BorrowingRepository borrowingRepository;
    private final BookCopyRepository bookCopyRepository;
    private final LibrarianRepository librarianRepository;
    private final BorrowingBusinessRules rules;
    private final BorrowingMapper mapper;

    public UpdateBorrowingCommandHandler(BorrowingRepository borrowingRepository,
                                         BookCopyRepository bookCopyRepository,
                                         LibrarianRepository librarianRepository,
                                         BorrowingBusinessRules rules,
                                         BorrowingMapper mapper) {
        this.borrowingRepository = borrowingRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.librarianRepository = librarianRepository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public UpdateBorrowingResponse handle(UpdateBorrowingCommand command) {
        Borrowing borrowing = borrowingRepository.findById(command.id())
            .orElseThrow(() -> new RuntimeException("Borrowing not found: " + command.id()));

        Librarian returnedToLibrarian = null;
        if (command.returnedToLibrarianId() != null) {
            returnedToLibrarian = librarianRepository.findById(command.returnedToLibrarianId())
                .orElseThrow(() -> new RuntimeException("Returned to librarian not found: " + command.returnedToLibrarianId()));
        }

        if (command.borrowStatus() == null) {
            throw new RuntimeException("Borrow status cannot be null");
        }

        borrowing = mapper.borrowingFromUpdateCommand(borrowing, command, returnedToLibrarian);

        if (command.borrowStatus() == BorrowStatus.RETURNED) {
            borrowing.getBookCopy().setStatus(BookCopyStatus.AVAILABLE);
            bookCopyRepository.save(borrowing.getBookCopy());
        }

        borrowing = borrowingRepository.save(borrowing);
        return mapper.updateBorrowingResponseFromBorrowing(borrowing);
    }
}
