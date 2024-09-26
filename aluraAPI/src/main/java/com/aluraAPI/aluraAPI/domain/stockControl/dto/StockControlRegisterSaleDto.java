package com.aluraAPI.aluraAPI.domain.stockControl.dto;

import jakarta.validation.constraints.NotNull;

public record StockControlRegisterSaleDto(
        @NotNull
        float quantity,
        @NotNull
        String type,
        @NotNull
        long productId,
        @NotNull
        long saleProductId,
        @NotNull
        long userId,
        @NotNull
        long stockId

) {
}
