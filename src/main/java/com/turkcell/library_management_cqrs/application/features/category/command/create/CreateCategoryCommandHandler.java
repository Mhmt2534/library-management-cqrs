package com.turkcell.library_management_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.category.mapper.CategoryMapper;
import com.turkcell.library_management_cqrs.application.features.category.rule.CategoryBusinessRules;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.domain.category.Category;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, CreatedCategoryResponse> {
    private final CategoryRepository repsitory;
    private final CategoryMapper mapper;
    private final CategoryBusinessRules rules;

    public CreateCategoryCommandHandler(CategoryRepository repsitory,  CategoryMapper mapper, CategoryBusinessRules rules) {
        this.repsitory = repsitory;
        this.mapper = mapper;
        this.rules = rules;
    }


    @Override
    public CreatedCategoryResponse handle(CreateCategoryCommand command) {
        Category category = mapper.categoryFromCreateCommand(command);
        rules.categoryWithSameNameMustNotExist(category.getCategoryName());
        repsitory.save(category);
        
        return mapper.createCategoryResponseFromCategory(category);
    }

}
