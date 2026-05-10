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

import com.turkcell.library_management_cqrs.application.features.librarian.command.create.CreateLibrarianCommand;
import com.turkcell.library_management_cqrs.application.features.librarian.command.create.CreateLibrarianResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.command.delete.DeleteLibrarianCommand;
import com.turkcell.library_management_cqrs.application.features.librarian.command.delete.DeleteLibrarianResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.command.update.UpdateLibrarianCommand;
import com.turkcell.library_management_cqrs.application.features.librarian.command.update.UpdateLibrarianResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.query.getall.GetAllLibrariansQuery;
import com.turkcell.library_management_cqrs.application.features.librarian.query.getall.GetAllLibrariansResponse;
import com.turkcell.library_management_cqrs.application.features.librarian.query.getbyid.GetLibrarianByIdQuery;
import com.turkcell.library_management_cqrs.application.features.librarian.query.getbyid.GetLibrarianByIdResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/librarians")
public class LibrariansController {

    private final Mediator mediator;

    public LibrariansController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public CreateLibrarianResponse create(@Valid @RequestBody CreateLibrarianCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/update")
    public UpdateLibrarianResponse update(@Valid @RequestBody UpdateLibrarianCommand command) {
        return mediator.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteLibrarianResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeleteLibrarianCommand(id));
    }

    @GetMapping("/getAll")
    public Page<GetAllLibrariansResponse> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return mediator.send(new GetAllLibrariansQuery(pageNumber, pageSize));
    }

    @GetMapping("/getById/{id}")
    public GetLibrarianByIdResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetLibrarianByIdQuery(id));
    }
}
