package com.turkcell.library_management_cqrs.application.features.borrowing.command.delete;

public record DeleteBorrowingResponse(String message) {
    public static DeleteBorrowingResponse create() {
        return new DeleteBorrowingResponse("Borrowing deleted successfully");
    }
}
