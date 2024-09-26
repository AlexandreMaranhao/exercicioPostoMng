package com.aluraAPI.aluraAPI.domain.deal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DealUpdateDto(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        Date validity
) {
}
