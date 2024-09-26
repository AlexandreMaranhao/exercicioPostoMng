package com.aluraAPI.aluraAPI.domain.stockControl.dto;


import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StockControlRegisterEntranceDto(
        long id,
        LocalDateTime date,
        @NotNull
        float quantity,
        @NotNull
        String type,
        @NotNull
        long userId,
        @NotNull
        long stockId
) {
    public StockControlRegisterEntranceDto(StockControl stockControl) {
        this(stockControl.getId(),
                stockControl.getDate(),
                stockControl.getQuantity(),
                stockControl.getType(),
                stockControl.getUserId().getId(),
                stockControl.getStockId().getId()
        );
    }
}
