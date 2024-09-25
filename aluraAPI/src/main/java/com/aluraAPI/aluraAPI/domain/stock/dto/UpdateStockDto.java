package com.aluraAPI.aluraAPI.domain.stock.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateStockDto(
        @NotNull
        long id,
        @NotNull
        float quantity
) {
}
