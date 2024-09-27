package com.aluraAPI.aluraAPI.domain.stockControl.dto;

import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;
import com.aluraAPI.aluraAPI.domain.stockControl.Type;

import java.time.LocalDateTime;

public record StockControlListDto(
        long id,
        LocalDateTime date,
        float quantity,
        Type type,
        long saleProductId,
        long userId,
        long stockId
) {

    public StockControlListDto(StockControl listStockControlInput){
        this(listStockControlInput.getId(),
                listStockControlInput.getDate(),
                listStockControlInput.getQuantity(),
                listStockControlInput.getType(),
                listStockControlInput.getSaleProductId().getId(),
                listStockControlInput.getUserId().getId(),
                listStockControlInput.getStockId().getId()
        );
    }


}
