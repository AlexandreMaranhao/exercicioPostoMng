package com.aluraAPI.aluraAPI.domain.stockControl.dto;

import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;

import java.time.LocalDateTime;

public record StockControlListDto(
        long id,
        LocalDateTime date,
        float quantity,
        String type,
        long saleProductId,
        long userId,
        long stockId
) {

    public StockControlListDto(StockControl stockControlInput){
        this(stockControlInput.getId(),
                stockControlInput.getDate(),
                stockControlInput.getQuantity(),
                stockControlInput.getType(),
                stockControlInput.getSaleProductId().getId(),
                stockControlInput.getUserId().getId(),
                stockControlInput.getStockId().getId()
        );
    }


}
