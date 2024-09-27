package com.aluraAPI.aluraAPI.domain.stockControl.dto;

import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;
import com.aluraAPI.aluraAPI.domain.stockControl.Type;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StockControlRegisteredSaleDto(
        long id,
        LocalDateTime date,
        @NotNull
        float quantity,
        @NotNull
        Type type,
        @NotNull
        long saleProductId,
        @NotNull
        long userId,
        @NotNull
        long stockId
) {
    public StockControlRegisteredSaleDto(StockControl registredStockControl) {
        this(registredStockControl.getId(),
                registredStockControl.getDate(),
                registredStockControl.getQuantity(),
                registredStockControl.getType(),
                registredStockControl.getSaleProductId().getId(),
                registredStockControl.getUserId().getId(),
                registredStockControl.getStockId().getId()
        );
    }
}
