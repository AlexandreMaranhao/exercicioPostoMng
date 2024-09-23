package com.aluraAPI.aluraAPI.domain.category.dto;

import com.aluraAPI.aluraAPI.domain.category.Category;

public record ListCategoryDto(
        long id,
        String name
) {
    public ListCategoryDto(Category categoryInput) {
        this(categoryInput.getId(),
                categoryInput.getName()
        );
    }
}
