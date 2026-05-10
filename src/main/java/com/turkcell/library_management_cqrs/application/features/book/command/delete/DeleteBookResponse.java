package com.turkcell.library_management_cqrs.application.features.book.command.delete;

public record DeleteBookResponse(
    String message
) {
    public static DeleteBookResponse create() {
        return new DeleteBookResponse("Book deleted successfully");
    }
}