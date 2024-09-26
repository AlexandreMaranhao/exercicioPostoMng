package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoyaltyUpdateDto(
        @NotNull
        Long id,
        @NotBlank
        String number,
        int points
) {

}
