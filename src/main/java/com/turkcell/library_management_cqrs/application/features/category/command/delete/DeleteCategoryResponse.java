package com.turkcell.library_management_cqrs.application.features.category.command.delete;

import java.util.UUID;

public record DeleteCategoryResponse(UUID id, String categoryName) {}
