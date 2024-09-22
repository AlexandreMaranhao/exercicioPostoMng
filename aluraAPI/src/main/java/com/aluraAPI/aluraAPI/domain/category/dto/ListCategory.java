package com.aluraAPI.aluraAPI.domain.category.dto;

import com.aluraAPI.aluraAPI.domain.category.Category;

public record ListCategory(
        long id,
        String name
) {
    public ListCategory(Category categoryInput) {
        this(categoryInput.getId(),
                categoryInput.getName()
        );
    }
}
