package com.turkcell.library_management_cqrs.web.controller;

import java.util.UUID;

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

import com.turkcell.library_management_cqrs.application.features.bookcopy.command.create.CreateBookCopyCommand;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.create.CreateBookCopyResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.delete.DeleteBookCopyCommand;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.delete.DeleteBookCopyResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.update.UpdateBookCopyCommand;
import com.turkcell.library_management_cqrs.application.features.bookcopy.command.update.UpdateBookCopyResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.query.getall.GetAllBookCopiesQuery;
import com.turkcell.library_management_cqrs.application.features.bookcopy.query.getall.GetAllBookCopiesResponse;
import com.turkcell.library_management_cqrs.application.features.bookcopy.query.getbyid.GetBookCopyByIdQuery;
import com.turkcell.library_management_cqrs.application.features.bookcopy.query.getbyid.GetBookCopyByIdResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookcopies")
public class BookCopiesController {

    private final Mediator mediator;

    public BookCopiesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public CreateBookCopyResponse create(@Valid @RequestBody CreateBookCopyCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/update")
    public UpdateBookCopyResponse update(@Valid @RequestBody UpdateBookCopyCommand command) {
        return mediator.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteBookCopyResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeleteBookCopyCommand(id));
    }

    @GetMapping("/getAll")
    public Page<GetAllBookCopiesResponse> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return mediator.send(new GetAllBookCopiesQuery(pageNumber, pageSize));
    }

    @GetMapping("/getById/{id}")
    public GetBookCopyByIdResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetBookCopyByIdQuery(id));
    }
}