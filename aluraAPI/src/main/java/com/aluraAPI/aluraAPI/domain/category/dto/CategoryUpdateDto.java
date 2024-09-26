package com.aluraAPI.aluraAPI.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateDto(
        @NotNull
        Long id,
        @NotBlank
        String name
) {

}
