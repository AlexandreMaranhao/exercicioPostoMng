package com.aluraAPI.aluraAPI.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterProduct(
        @NotBlank
        String name,

        float price,
        @NotNull
        long categoryId
)
    {
}
