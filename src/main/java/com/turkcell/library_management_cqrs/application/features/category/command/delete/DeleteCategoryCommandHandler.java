package com.turkcell.library_management_cqrs.application.features.category.command.delete;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class DeleteCategoryCommandHandler implements CommandHandler<DeleteCategoryCommand, DeleteCategoryResponse> {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public DeleteCategoryCommandHandler(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DeleteCategoryResponse handle(DeleteCategoryCommand command) {
        var category = repository.findById(command.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + command.categoryId()));

        repository.delete(category);

        return mapper.deleteCategoryResponseFromCategory(category);
    }

}
