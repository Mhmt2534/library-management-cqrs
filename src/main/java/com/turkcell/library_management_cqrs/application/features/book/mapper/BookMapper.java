package com.turkcell.library_management_cqrs.application.features.book.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.book.command.create.CreateBookCommand;
import com.turkcell.library_management_cqrs.application.features.book.command.create.CreateBookResponse;
import com.turkcell.library_management_cqrs.application.features.book.command.update.UpdateBookCommand;
import com.turkcell.library_management_cqrs.application.features.book.command.update.UpdateBookResponse;
import com.turkcell.library_management_cqrs.application.features.book.query.getall.GetAllBooksResponse;
import com.turkcell.library_management_cqrs.application.features.book.query.getbyid.GetBookByIdResponse;
import com.turkcell.library_management_cqrs.domain.book.Book;

@Component
public class BookMapper {

    public Book bookFromCreateCommand(CreateBookCommand command) {
        Book book = new Book();
        book.setBookName(command.bookName());
        book.setAuthorName(command.authorName());
        book.setShelfNumber(command.shelfNumber());
        book.setQuantityInStock(command.quantityInStock());
        return book;
    }

    public Book bookFromUpdateCommand(Book book, UpdateBookCommand command) {
        book.setBookName(command.bookName());
        book.setAuthorName(command.authorName());
        book.setShelfNumber(command.shelfNumber());
        book.setQuantityInStock(command.quantityInStock());
        return book;
    }

    public CreateBookResponse createBookResponseFromBook(Book book) {
        return new CreateBookResponse(
            book.getId(),
            book.getBookName(),
            book.getAuthorName(),
            book.getShelfNumber(),
            book.getQuantityInStock()
        );
    }

    public UpdateBookResponse updateBookResponseFromBook(Book book) {
        return new UpdateBookResponse(
            book.getId(),
            book.getBookName(),
            book.getAuthorName(),
            book.getShelfNumber(),
            book.getQuantityInStock()
        );
    }

    public GetAllBooksResponse getAllBooksResponseFromBook(Book book) {
        return new GetAllBooksResponse(
            book.getId(),
            book.getBookName(),
            book.getAuthorName(),
            book.getShelfNumber(),
            book.getQuantityInStock()
        );
    }

    public GetBookByIdResponse getBookByIdResponseFromBook(Book book) {
        var categoryNames = book.getCategories().stream()
            .map(category -> category.getCategoryName())
            .collect(Collectors.toSet());
        return new GetBookByIdResponse(
            book.getId(),
            book.getBookName(),
            book.getAuthorName(),
            book.getShelfNumber(),
            book.getQuantityInStock(),
            categoryNames
        );
    }
}