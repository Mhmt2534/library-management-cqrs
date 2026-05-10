package com.turkcell.library_management_cqrs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_management_cqrs.application.features.category.command.create.CreateCategoryCommand;
import com.turkcell.library_management_cqrs.application.features.category.command.create.CreatedCategoryResponse;
import com.turkcell.library_management_cqrs.application.features.category.command.delete.DeleteCategoryCommand;
import com.turkcell.library_management_cqrs.application.features.category.command.delete.DeleteCategoryResponse;
import com.turkcell.library_management_cqrs.application.features.category.command.update.UpdateCategoryCommand;
import com.turkcell.library_management_cqrs.application.features.category.command.update.UpdateCategoryResponse;
import com.turkcell.library_management_cqrs.application.features.category.query.getall.GetAllCategoriesQuery;
import com.turkcell.library_management_cqrs.application.features.category.query.getall.GetAllCategoriesResponse;
import com.turkcell.library_management_cqrs.core.mediator.Mediator;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/categories")
public class CatgeoriesController {
    private final Mediator mediator;

    public CatgeoriesController(Mediator mediator) {
        this.mediator = mediator;
    }
    
    @PostMapping
    public CreatedCategoryResponse create(@RequestBody CreateCategoryCommand command) {

        return mediator.send(command);
    }

    @GetMapping
    public Page<GetAllCategoriesResponse> getAll(
        @RequestParam(defaultValue="0") int pageNumber,
        @RequestParam(defaultValue="10") int pageSize
    ) {
        var query = new GetAllCategoriesQuery(pageNumber, pageSize);

        return mediator.send(query);
    }

    @PutMapping
    public UpdateCategoryResponse putMethodName(@RequestBody UpdateCategoryCommand command) {
        
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    public DeleteCategoryResponse delete(@PathVariable UUID id) {
        var command = new DeleteCategoryCommand(id);
        return mediator.send(command);
    }

}
