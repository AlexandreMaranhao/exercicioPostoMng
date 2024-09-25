package com.aluraAPI.aluraAPI.domain.product.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;

public record ListProductDto(
        long id,
        String name,
        float price,
        long categoryId,
        boolean active
)
{

    public ListProductDto(Product productInput){
        this(productInput.getId(),
                productInput.getName(),
                productInput.getPrice(),
                productInput.getCategory().getId(),
                productInput.isActive()
        );
    }

}
