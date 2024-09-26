package com.aluraAPI.aluraAPI.domain.stock.dto;

import com.aluraAPI.aluraAPI.domain.stock.Stock;

import java.time.LocalDateTime;

public record StockListDto(
        long id,
        float quantity,
        LocalDateTime validity,
        long productId

) {
    public StockListDto(Stock stockInput) {
        this(stockInput.getId(),
                stockInput.getQuantity(),
                stockInput.getValidity(),
                stockInput.getProductId().getId()
        );
    }
}
