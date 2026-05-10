package com.turkcell.library_management_cqrs.application.features.category.command.create;

import java.util.UUID;

public record CreatedCategoryResponse(UUID id, String categoryName) {}
