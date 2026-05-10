package com.turkcell.library_management_cqrs.application.features.bookcopy.command.delete;

public record DeleteBookCopyResponse(
    String message
) {
    public static DeleteBookCopyResponse create() {
        return new DeleteBookCopyResponse("Book copy deleted successfully");
    }
}