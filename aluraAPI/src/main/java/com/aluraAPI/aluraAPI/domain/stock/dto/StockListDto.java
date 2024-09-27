package com.aluraAPI.aluraAPI.domain.stock.dto;

import com.aluraAPI.aluraAPI.domain.stock.Stock;

import java.time.LocalDateTime;

public record StockListDto(
        long id,
        float quantity,
        LocalDateTime validity,
        long productId

) {
    public StockListDto(Stock listStockInput) {
        this(listStockInput.getId(),
                listStockInput.getQuantity(),
                listStockInput.getValidity(),
                listStockInput.getProductId().getId()
        );
    }
}
