package com.turkcell.library_management_cqrs.application.features.category.rule;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.domain.category.Category;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class CategoryBusinessRules {
private final CategoryRepository categoryRepository;

    public CategoryBusinessRules(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void categoryWithSameNameMustNotExist(String categoryName){
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName).orElse(null);

        if(categoryWithSameName!=null)
            throw new RuntimeException("Ayni isimde 2 kategori eklenemez");
    }



}
