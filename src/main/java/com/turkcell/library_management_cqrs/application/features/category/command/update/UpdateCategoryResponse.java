package com.turkcell.library_management_cqrs.application.features.category.command.update;

import java.util.UUID;

public record UpdateCategoryResponse(UUID id,String categoryName) {}
