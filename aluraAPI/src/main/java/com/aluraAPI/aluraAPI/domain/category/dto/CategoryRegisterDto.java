package com.aluraAPI.aluraAPI.domain.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRegisterDto(
        @NotBlank
        String name
) {

}
