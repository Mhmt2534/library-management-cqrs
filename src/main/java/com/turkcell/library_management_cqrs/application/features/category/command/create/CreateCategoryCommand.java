package com.turkcell.library_management_cqrs.application.features.category.command.create;


import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

public record CreateCategoryCommand(String categoryName) implements Command<CreatedCategoryResponse> {}
