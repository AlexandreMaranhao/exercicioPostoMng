package com.aluraAPI.aluraAPI.domain.product.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;

public record ListProductDto(
        long id,
        String name,
        float price,
        long categoryId,//TODO: Mudar modo de chamada da FK
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
