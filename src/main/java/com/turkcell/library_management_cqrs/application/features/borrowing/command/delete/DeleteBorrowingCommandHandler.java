package com.turkcell.library_management_cqrs.application.features.borrowing.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.BorrowingRepository;

@Component
public class DeleteBorrowingCommandHandler implements CommandHandler<DeleteBorrowingCommand, DeleteBorrowingResponse> {

    private final BorrowingRepository borrowingRepository;

    public DeleteBorrowingCommandHandler(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    @Override
    public DeleteBorrowingResponse handle(DeleteBorrowingCommand command) {
        borrowingRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Borrowing not found: " + command.id()));
        borrowingRepository.deleteById(command.id());
        return DeleteBorrowingResponse.create();
    }
}
