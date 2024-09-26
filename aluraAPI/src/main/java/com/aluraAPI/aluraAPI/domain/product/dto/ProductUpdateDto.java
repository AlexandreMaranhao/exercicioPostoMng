package com.aluraAPI.aluraAPI.domain.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductUpdateDto(
        @NotNull
        Long id,

        String name,

        float price,

        long categoryId

) {
}
