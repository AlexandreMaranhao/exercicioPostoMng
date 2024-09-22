package com.aluraAPI.aluraAPI.domain.costumer.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterCostumer(
        @NotBlank
        String cpf,

        String name,

        long loyaltyId
)
    {
}
