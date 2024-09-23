package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterLoyaltyDto(
        @NotBlank
        String number,
        int points
) {

}
