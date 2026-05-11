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

import com.turkcell.library_management_cqrs.application.features.penalty.command.create.CreatePenaltyCommand;
import com.turkcell.library_management_cqrs.application.features.penalty.command.create.CreatePenaltyResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.command.delete.DeletePenaltyCommand;
import com.turkcell.library_management_cqrs.application.features.penalty.command.delete.DeletePenaltyResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.command.update.UpdatePenaltyCommand;
import com.turkcell.library_management_cqrs.application.features.penalty.command.update.UpdatePenaltyResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.query.getall.GetAllPenaltiesQuery;
import com.turkcell.library_management_cqrs.application.features.penalty.query.getall.GetAllPenaltiesResponse;
import com.turkcell.library_management_cqrs.application.features.penalty.query.getbyid.GetPenaltyByIdQuery;
import com.turkcell.library_management_cqrs.application.features.penalty.query.getbyid.GetPenaltyByIdResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/penalties")
public class PenaltiesController {

    private final Mediator mediator;

    public PenaltiesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/create")
    public CreatePenaltyResponse create(@Valid @RequestBody CreatePenaltyCommand command) {
        return mediator.send(command);
    }

    @PutMapping("/update")
    public UpdatePenaltyResponse update(@Valid @RequestBody UpdatePenaltyCommand command) {
        return mediator.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public DeletePenaltyResponse delete(@PathVariable UUID id) {
        return mediator.send(new DeletePenaltyCommand(id));
    }

    @GetMapping("/getAll")
    public Page<GetAllPenaltiesResponse> getAll(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return mediator.send(new GetAllPenaltiesQuery(pageNumber, pageSize));
    }

    @GetMapping("/getById/{id}")
    public GetPenaltyByIdResponse getById(@PathVariable UUID id) {
        return mediator.send(new GetPenaltyByIdQuery(id));
    }
}
