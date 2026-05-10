package com.turkcell.library_management_cqrs.application.features.category.command.update;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_management_cqrs.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, UpdateCategoryResponse> {

    private final CategoryRepository repository;
    private final CategoryBusinessRules rules;
    private final CategoryMapper mapper;

    public UpdateCategoryCommandHandler(CategoryRepository repository, CategoryBusinessRules rules, CategoryMapper mapper) {
        this.repository = repository;
        this.rules = rules;
        this.mapper = mapper;
    }

    @Override
    public UpdateCategoryResponse handle(UpdateCategoryCommand command) {
        var category = repository.findById(command.id()).orElseThrow();
        category = mapper.categoryFromUpdateCommand(category, command.categoryName());
        
        rules.categoryWithSameNameMustNotExist(category.getCategoryName());
        repository.save(category);
        
        return mapper.updateCategoryResponseFromCategory(category);
    }
}
