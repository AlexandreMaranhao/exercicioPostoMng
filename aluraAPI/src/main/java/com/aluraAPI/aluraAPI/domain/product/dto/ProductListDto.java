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

    public ProductListDto(Product listProductInput){
        this(listProductInput.getId(),
                listProductInput.getName(),
                listProductInput.getPrice(),
                listProductInput.getCategory().getId(),
                listProductInput.isActive()
        );
    }

}
