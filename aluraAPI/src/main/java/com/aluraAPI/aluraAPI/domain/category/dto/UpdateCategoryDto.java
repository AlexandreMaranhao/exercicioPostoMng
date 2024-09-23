package com.aluraAPI.aluraAPI.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryDto(
        @NotNull
        Long id,
        @NotBlank
        String name
) {

}
