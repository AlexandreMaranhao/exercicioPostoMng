package com.aluraAPI.aluraAPI.domain.costumer.dto;

import jakarta.validation.constraints.NotNull;

public record CostumerUpdateDto(
        @NotNull
        Long id,

        String cpf,

        String name,

        Long loyaltyId

) {
}
