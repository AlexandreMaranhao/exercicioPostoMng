package com.aluraAPI.aluraAPI.domain.product.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetailProduct(
        @NotNull
        long id,
        @NotBlank
        String name,
        float price,
        @NotNull
        long categoryId
) {
    public DetailProduct(Product product) {
        this(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategoryId().getId()
        );
    }
}
