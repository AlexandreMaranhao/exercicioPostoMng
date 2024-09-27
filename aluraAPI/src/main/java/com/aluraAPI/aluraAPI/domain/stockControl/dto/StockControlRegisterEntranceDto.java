package com.aluraAPI.aluraAPI.domain.stockControl.dto;


import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;
import com.aluraAPI.aluraAPI.domain.stockControl.Type;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StockControlRegisterEntranceDto(
        long id,
        LocalDateTime date,
        @NotNull
        float quantity,

        Type type,
        @NotNull
        long userId,
        @NotNull
        long stockId
) {
    public StockControlRegisterEntranceDto(StockControl registredStockControl) {
        this(registredStockControl.getId(),
                registredStockControl.getDate(),
                registredStockControl.getQuantity(),
                registredStockControl.getType(),
                registredStockControl.getUserId().getId(),
                registredStockControl.getStockId().getId()
        );
    }
}
