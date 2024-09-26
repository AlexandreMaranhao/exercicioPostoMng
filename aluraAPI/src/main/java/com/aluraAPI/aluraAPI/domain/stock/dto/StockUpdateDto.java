package com.aluraAPI.aluraAPI.domain.stock.dto;

import jakarta.validation.constraints.NotNull;

public record StockUpdateDto(
        @NotNull
        long id,
        @NotNull
        float quantity
) {
}
