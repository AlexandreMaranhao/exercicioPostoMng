package com.aluraAPI.aluraAPI.domain.product.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterDto(
        @NotNull
        long id,
        @NotBlank
        String name,
        float price,
        @NotNull
        long categoryId
) {

}
