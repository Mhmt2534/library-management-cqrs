package com.turkcell.library_management_cqrs.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_management_cqrs.application.features.book.command.create.CreateBookCommand;
import com.turkcell.library_management_cqrs.application.features.book.command.create.CreateBookResponse;
import com.turkcell.library_management_cqrs.application.features.book.command.delete.DeleteBookCommand;
import com.turkcell.library_management_cqrs.application.features.book.command.delete.DeleteBookResponse;
import com.turkcell.library_management_cqrs.application.features.book.command.update.UpdateBookCommand;
import com.turkcell.library_management_cqrs.application.features.book.command.update.UpdateBookResponse;
import com.turkcell.library_management_cqrs.application.features.book.query.getall.GetAllBooksQuery;
import com.turkcell.library_management_cqrs.application.features.book.query.getall.GetAllBooksResponse;
import com.turkcell.library_management_cqrs.application.features.book.query.getbyid.GetBookByIdQuery;
import com.turkcell.library_management_cqrs.application.features.book.query.getbyid.GetBookByIdResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final Mediator mediator;

    public BooksController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public CreateBookResponse create(@Valid @RequestBody CreateBookCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/update")
    public UpdateBookResponse update(@Valid @RequestBody UpdateBookCommand command) {
        return mediator.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteBookResponse delete(@PathVariable UUID id) {
        var command = new DeleteBookCommand(id);
        return mediator.send(command);
    }

    @GetMapping("/getAll")
    public Page<GetAllBooksResponse> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        var query = new GetAllBooksQuery(pageNumber, pageSize);
        return mediator.send(query);
    }

    @GetMapping("/getById/{id}")
    public GetBookByIdResponse getById(@PathVariable UUID id) {
        var query = new GetBookByIdQuery(id);
        return mediator.send(query);
    }
}