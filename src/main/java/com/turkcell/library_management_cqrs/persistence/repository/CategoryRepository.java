package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    public Optional<Category> findByCategoryName(String categoryName);
}
