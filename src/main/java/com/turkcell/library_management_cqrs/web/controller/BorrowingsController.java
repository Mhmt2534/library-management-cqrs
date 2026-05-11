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

import com.turkcell.library_management_cqrs.application.features.borrowing.command.create.CreateBorrowingCommand;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.create.CreateBorrowingResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.delete.DeleteBorrowingCommand;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.delete.DeleteBorrowingResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.update.UpdateBorrowingCommand;
import com.turkcell.library_management_cqrs.application.features.borrowing.command.update.UpdateBorrowingResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.query.getall.GetAllBorrowingsQuery;
import com.turkcell.library_management_cqrs.application.features.borrowing.query.getall.GetAllBorrowingsResponse;
import com.turkcell.library_management_cqrs.application.features.borrowing.query.getbyid.GetBorrowingByIdQuery;
import com.turkcell.library_management_cqrs.application.features.borrowing.query.getbyid.GetBorrowingByIdResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingsController {

    private final Mediator mediator;

    public BorrowingsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public CreateBorrowingResponse create(@Valid @RequestBody CreateBorrowingCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/update")
    public UpdateBorrowingResponse update(@Valid @RequestBody UpdateBorrowingCommand command) {
        return mediator.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteBorrowingResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeleteBorrowingCommand(id));
    }

    @GetMapping("/getAll")
    public Page<GetAllBorrowingsResponse> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return mediator.send(new GetAllBorrowingsQuery(pageNumber, pageSize));
    }

    @GetMapping("/getById/{id}")
    public GetBorrowingByIdResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetBorrowingByIdQuery(id));
    }
}
