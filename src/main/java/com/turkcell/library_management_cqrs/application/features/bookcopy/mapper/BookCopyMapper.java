package com.turkcell.library_management_cqrs.application.features.bookcopy.mapper;

import java.util.Set;

import com.turkcell.library_management_cqrs.application.features.bookcopy.command.create.CreateBookCopyCommand;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.create.CreateBookCopyResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.update.UpdateBookCopyCommand;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.update.UpdateBookCopyResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.query.getall.GetAllBookCopiesResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.query.getbyid.GetBookCopyByIdResponse;
import com.turkcell.library_management_cqrs.domain.book.Book;
import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

    public BookCopy bookCopyFromCreateCommand(CreateBookCopyCommand command, Book book) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBook(book);
        bookCopy.setCode(command.code());
        return bookCopy;
    }

    public BookCopy bookCopyFromUpdateCommand(BookCopy bookCopy, UpdateBookCopyCommand command) {
        bookCopy.setCode(command.code());
        bookCopy.setStatus(command.status());
        return bookCopy;
    }

    public CreateBookCopyResponse createBookCopyResponseFromBookCopy(BookCopy bookCopy) {
        return new CreateBookCopyResponse(bookCopy.getBook().getId(), 1, Set.of(bookCopy.getId()));
    }

    public UpdateBookCopyResponse updateBookCopyResponseFromBookCopy(BookCopy bookCopy) {
        return new UpdateBookCopyResponse(
            bookCopy.getId(),
            bookCopy.getCode(),
            bookCopy.getStatus().name(),
            bookCopy.getBook().getId()
        );
    }

    public GetAllBookCopiesResponse getAllBookCopiesResponseFromBookCopy(BookCopy bookCopy) {
        return new GetAllBookCopiesResponse(
            bookCopy.getId(),
            bookCopy.getCode(),
            bookCopy.getStatus(),
            bookCopy.getBook().getId()
        );
    }

    public GetBookCopyByIdResponse getBookCopyByIdResponseFromBookCopy(BookCopy bookCopy) {
        return new GetBookCopyByIdResponse(
            bookCopy.getId(),
            bookCopy.getCode(),
            bookCopy.getStatus(),
            bookCopy.getBook().getId()
        );
    }
}