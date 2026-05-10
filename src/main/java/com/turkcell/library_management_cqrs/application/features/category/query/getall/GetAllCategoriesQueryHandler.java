package com.turkcell.library_management_cqrs.application.features.category.query.getall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.domain.category.Category;
import com.turkcell.library_management_cqrs.persistence.repository.CategoryRepository;

@Component
public class GetAllCategoriesQueryHandler implements QueryHandler<GetAllCategoriesQuery, Page<GetAllCategoriesResponse>>{
    private final CategoryRepository categoryRepository;
    

    public GetAllCategoriesQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Page<GetAllCategoriesResponse> handle(GetAllCategoriesQuery query) {
        Pageable pageable = PageRequest.of(query.pageNumber(), query.pageSize());

        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories.map(category -> new GetAllCategoriesResponse(category.getId(), category.getCategoryName()));
    }

}
