package com.aluraAPI.aluraAPI.domain.costumer.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateCostumer(
        @NotNull
        Long id,

        String cpf,

        String name,

        long loyaltyId

) {
}
