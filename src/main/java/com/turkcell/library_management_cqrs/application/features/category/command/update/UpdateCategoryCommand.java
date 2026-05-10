package com.turkcell.library_management_cqrs.application.features.category.command.update;

import java.util.UUID;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;

public record UpdateCategoryCommand(UUID id, String categoryName) implements Command<UpdateCategoryResponse> {}
