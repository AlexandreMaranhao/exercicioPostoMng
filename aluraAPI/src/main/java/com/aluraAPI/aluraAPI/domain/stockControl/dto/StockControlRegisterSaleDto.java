package com.aluraAPI.aluraAPI.domain.stockControl.dto;

import com.aluraAPI.aluraAPI.domain.stockControl.Type;
import jakarta.validation.constraints.NotNull;

public record StockControlRegisterSaleDto(
        @NotNull
        float quantity,

        Type type,
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
