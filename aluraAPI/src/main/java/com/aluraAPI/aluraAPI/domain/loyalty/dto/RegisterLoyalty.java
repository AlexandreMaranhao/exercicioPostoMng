package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterLoyalty(
        @NotBlank
        String number,
        int points
) {

}
