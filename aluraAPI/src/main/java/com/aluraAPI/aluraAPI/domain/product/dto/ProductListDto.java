package com.aluraAPI.aluraAPI.domain.product.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;

public record ProductListDto(
        long id,
        String name,
        float price,
        long categoryId,
        boolean active
)
{

    public ProductListDto(Product productInput){
        this(productInput.getId(),
                productInput.getName(),
                productInput.getPrice(),
                productInput.getCategory().getId(),
                productInput.isActive()
        );
    }

}
