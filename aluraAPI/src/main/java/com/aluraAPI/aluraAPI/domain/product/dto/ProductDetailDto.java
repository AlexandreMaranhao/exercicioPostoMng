package com.aluraAPI.aluraAPI.domain.product.dto;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.product.Product;

public record ProductDetailDto(
        long id,
        String name,
        float price,
        Category categoryId,
        boolean active
) {

}
