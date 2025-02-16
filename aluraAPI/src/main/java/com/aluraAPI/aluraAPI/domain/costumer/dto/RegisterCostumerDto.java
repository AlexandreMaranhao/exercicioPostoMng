package com.aluraAPI.aluraAPI.domain.costumer.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterCostumerDto(
        @NotBlank
        String cpf,

        String name,

        long loyaltyId
)
    {
}
