package com.turkcell.library_management_cqrs.application.features.category.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.application.features.category.command.create.CreateCategoryCommand;
import com.turkcell.library_management_cqrs.application.features.category.command.create.CreatedCategoryResponse;
import com.turkcell.library_management_cqrs.application.features.category.command.delete.DeleteCategoryResponse;
import com.turkcell.library_management_cqrs.application.features.category.command.update.UpdateCategoryResponse;
import com.turkcell.library_management_cqrs.domain.category.Category;

@Component
public class CategoryMapper {
 public Category categoryFromCreateCommand(CreateCategoryCommand command){
        Category category = new Category();
        category.setCategoryName(command.categoryName());
        return category;
    }

    public CreatedCategoryResponse createCategoryResponseFromCategory(Category category)
    {
        return new CreatedCategoryResponse(category.getId(),category.getCategoryName());
    }

    public DeleteCategoryResponse deleteCategoryResponseFromCategory(Category category) {
        return new DeleteCategoryResponse(category.getId(), category.getCategoryName());
    }
    
    public UpdateCategoryResponse updateCategoryResponseFromCategory(Category category) {
        return new UpdateCategoryResponse(category.getId(), category.getCategoryName());
    }

    public Category categoryFromUpdateCommand(Category category, String categoryName){
        category.setCategoryName(categoryName);
        return category;
    }

}
